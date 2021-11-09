/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edgedetection;

/**
 *
 * @author Adit,Diaz,Angga
 */
import java.awt.image.BufferedImage;

public class Edge {
    private static final int GAUSSIAN_RADIUS = 7;
    private static final double GAUSSIAN_INTENSITY = 1.5;
    private static final double SQRT2PI = Math.sqrt(2 * Math.PI);
    private static final int[][] MASK_H = { {-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1} }; //Operation Sobel Horizontal
    private static final int[][] MASK_V = { {-1, -2, -1}, {0, 0, 0}, {1, 2, 1} }; //Operation Sobel Vertikal
    
    private static int stDev;       //Standard deviation in magnitude of image's pixels
    private static int mean;        //Mean of magnitude in image's pixels
    private static int numDev;      //Number of standard deviations above mean for high threshold
    private static double tHi;      //Hysteresis high threshold; Definitely edge pixels, do not examine
    private static double tLo;      //Hysteresis low threshold; possible edge pixel, examine further.
    private static double tFract;   //Low threshold is this fraction of high threshold
    private static int[][] dir;     //Gradient direction mask. Equals Math.atan2(gy/gx)
    private static int[][] gx;      //Mask resulting from horizontal 3x3 Sobel mask
    private static int[][] gy;      //Mask resulting from vertical 3x3 Sobel mask
    private static double[][] mag;  //Direction mask. Equals Math.sqrt(gx^2 * gy^2)
    
    /**
     * This function accepts a single-channel (grayscale, red, blue, Y, etc) image and returns an image with detected edges.
     * Currently computes hysteresis thresholds based on an a given ratio, but in the future all parameters will be passed 
     * in from an external source to allow another program to optimize them.
     * 
     * @param img               A BufferedImage that is to undergo Edge edge detector. 
     * @param numberDeviations  Set high threshold as a function of number of standard deviations above the mean.
     *                          mean + std. dev: 68% of pixel magnitudes fall below this value
     *                          mean + 2 * std. dev: 95% of pixel magnitudes fall below this value
     *                          mean + 3 * std. dev: 99.7% of pixel magnitudes fall below this value
     * @param fract             Set low threshold as a fraction of the high threshold
     * @return edges            A binary image of the edges in the input image.
     */
    public static BufferedImage CannyEdges(BufferedImage img, int numberDeviations, double fract) {
        int[][] raw = null;
        int[][] blurred = null;
        BufferedImage edges = null;
        numDev = numberDeviations;
        tFract = fract;
        
        //More specific bounds checking later
        if (img != null && numberDeviations > 0 && fract > 0) {
            raw = GSArray(img);
            blurred = GaussianBlurGS(raw, GAUSSIAN_RADIUS, GAUSSIAN_INTENSITY);
            gx = ConvHorizontal(blurred);  //Convolved with 3x3 horizontal Sobel mask
            gy = ConvVertical(blurred);    //Convolved with 3x3 vertical Sobel mask

            Magnitude();    //Find the gradient magnitude at each pixel
            Direction();    //Find the gradient direction at each pixel
            Suppression();  //Using the direction and magnitude images, identify candidate points

            edges = GSImg(Hysteresis());
        }
        
        return edges;
    }
    
    public static BufferedImage SobelEdgesHorizontal(BufferedImage img) {
        int[][] raw = null;
        int[][] blurred = null;
        BufferedImage edges = null;

        //More specific bounds checking later
        if (img != null) {
            raw = GSArray(img);
            blurred = GaussianBlurGS(raw, GAUSSIAN_RADIUS, GAUSSIAN_INTENSITY);
            gx = ConvHorizontal(blurred);  //Convolved with 3x3 horizontal Sobel mask

            edges = GSImg(gx);
        }
        
        return edges;
    }
        
    public static BufferedImage SobelEdgesVertikal(BufferedImage img) {
        int[][] raw = null;
        int[][] blurred = null;
        BufferedImage edges = null;
        
        //More specific bounds checking later
        if (img != null) {
            raw = GSArray(img);
            blurred = GaussianBlurGS(raw, GAUSSIAN_RADIUS, GAUSSIAN_INTENSITY);
            gy = ConvVertical(blurred);    //Convolved with 3x3 vertical Sobel mask

            edges = GSImg(gy);
        }
        
        return edges;
    }
    
    public static BufferedImage GaussianEdge(BufferedImage img) {
        int[][] raw = null;
        int[][] blurred = null;
        BufferedImage edges = null;
        
        if (img != null) {
            raw = GSArray(img);
            blurred = GaussianBlurGS(raw, GAUSSIAN_RADIUS, GAUSSIAN_INTENSITY);

            edges = GSImg(blurred);
        }
        
        return edges;
    }
    /**
     * Send this method a BufferedImage to get a grayscale array (int, value 0-255.
     * 
     * @param img   BufferedImage, the input image from which to extract grayscale
     * @return gs   int[][] array of grayscale pixel values from image.
     */
    public static int[][] GSArray(BufferedImage img) {
        int[][] gs = null;
        int height = img.getHeight();
        int width = img.getWidth();
        
        if (height > 0 && width > 0) {
            gs = new int[height][width];

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int bits = img.getRGB(j, i);
                    //Not sure if precision is needed, but adding for now
                    long avg = Math.round((((bits >> 16) & 0xff) + ((bits >> 8) & 0xff) + (bits & 0xff)) / 3.0);
                    gs[i][j] = (int) avg;
                }
            }
        }
        
        return gs;
    }
    
    /**
     * Send this method an int[][] grayscale array, an int radius, and a double intensity to blur the
     * image with a Gaussian filter of that radius and intensity.
     * 
     * @param raw       int[][], an array of grayscale values to be blurred
     * @param rad       int, the radius of the Gaussian filter (filter width = 2 * r + 1)
     * @param intens    double, the intensity of the Gaussian blur
     * @return outRGB   int[][], an array of grayscale values from blurring input image with Gaussian filter
     */
    public static int[][] GaussianBlurGS (int[][] raw, int rad, double intens) {
        int height = raw.length;
        int width = raw[0].length;
        double norm = 0.;
        double intensSquared2 = 2 * intens * intens;
        //This also seems very costly, do it as little as possible
        double invIntensSqrPi = 1 / (SQRT2PI * intens);
        double[] mask = new double[2 * rad + 1];
        int[][] outGS = new int[height - 2 * rad][width - 2 * rad];
        
        //Create Gaussian kernel
        for (int x = -rad; x < rad + 1; x++) {
            double exp = Math.exp(-((x * x) / intensSquared2));
            
            mask[x + rad] = invIntensSqrPi * exp;
            norm += mask[x + rad];
        }
        
        //Convolve image with kernel horizontally
        for (int r = rad; r < height - rad; r++) {
            for (int c = rad; c < width - rad; c++) {
                double sum = 0.;
                
                for (int mr = -rad; mr < rad + 1; mr++) {
                    sum += (mask[mr + rad] * raw[r][c + mr]);
                }
                
                //Normalize channel after blur
                sum /= norm;
                outGS[r - rad][c - rad] = (int) Math.round(sum);
            }
        }
        
        //Convolve image with kernel vertically
        for (int r = rad; r < height - rad; r++) {
            for (int c = rad; c < width - rad; c++) {
                double sum = 0.;
                
                for(int mr = -rad; mr < rad + 1; mr++) {
                    sum += (mask[mr + rad] * raw[r + mr][c]);
                }
                
                //Normalize channel after blur
                sum /= norm;
                outGS[r - rad][c - rad] = (int) Math.round(sum);
            }
        }
        
        return outGS;
    }
    
    /**
     * Send this method an int[][] array of grayscale pixel values to get a an image resulting
     * from the convolution of this image with the horizontal Sobel mask.
     * 
     * @param raw   int[][], array of grayscale pixel values 0-255
     * @return out  int[][], output array of convolved image.
     */
    public static int[][] ConvHorizontal(int[][] raw) {
        int[][] out = null;
        int height = raw.length;
        int width = raw[0].length;
        
        if (height > 2 && width > 2) {
            out = new int[height - 2][width - 2];
        
            for (int r = 1; r < height - 1; r++) {
                for (int c = 1; c < width - 1; c++) {
                    int sum = 0;

                    for (int kr = -1; kr < 2; kr++) {
                        for (int kc = -1; kc < 2; kc++) {
                            sum += (MASK_H[kr + 1][kc + 1] * raw[r + kr][c + kc]);
                        }
                    }

                    out[r - 1][c - 1] = sum;
                }
            }
        }
        
        return out;
    }
    
    /**
     * Send this method an int[][] array of grayscale pixel values to get a an image resulting
     * from the convolution of this image with the vertical Sobel mask.
     * 
     * @param raw   int[][], array of grayscale pixel values 0-255
     * @return out  int[][], output array of convolved image.
     */
    public static int[][] ConvVertical(int[][] raw) {
        int[][] out = null;
        int height = raw.length;
        int width = raw[0].length;
        
        if (height > 2 || width > 2) {
            out = new int[height - 2][width - 2];
        
            for (int r = 1; r < height - 1; r++) {
                for (int c = 1; c < width - 1; c++) {
                    int sum = 0;

                    for (int kr = -1; kr < 2; kr++) {
                        for (int kc = -1; kc < 2; kc++) {
                            sum += (MASK_V[kr + 1][kc + 1] * raw[r + kr][c + kc]);
                        }
                    }

                    out[r - 1][c - 1] = sum;
                }
            }
        }
        
        return out;
    } 
    
    /**
     * Send this method the horizontal and vertical Sobel convolutions to create the gradient magnitude image.
     * 
     * @return void
     */
    private static void Magnitude() {
        double sum = 0;
        double var = 0;
        int height = gx.length;
        int width = gx[0].length;
        double pixelTotal = height * width;
        mag = new double[height][width];
        
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                mag[r][c] = Math.sqrt(gx[r][c] * gx[r][c] + gy[r][c] * gy[r][c]);
                
                sum += mag[r][c];
            }
        }
        
        mean = (int) Math.round(sum / pixelTotal);
        
        //Get variance
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                double diff = mag[r][c] - mean;
                
                var += (diff * diff);
            }
        }
        
        stDev = (int) Math.sqrt(var / pixelTotal);
    }
    
    /**
     * Send this method the horizontal and vertical Sobel convolutions to create the gradient direction image.
     * 
     * @return void
     */
    private static void Direction() {
        int height = gx.length;
        int width = gx[0].length;
        double piRad = 180 / Math.PI;
        dir = new int[height][width];
        
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                double angle = Math.atan2(gy[r][c], gx[r][c]) * piRad;    //Convert radians to degrees
                
                //Check for negative angles
                if (angle < 0) {
                    angle += 360.;
                }
                
                //Each pixels ACTUAL angle is examined and placed in 1 of four groups (for the four searched 45-degree neighbors)
                //Reorder this for optimization
                if (angle <= 22.5 || (angle >= 157.5 && angle <= 202.5) || angle >= 337.5) {
                    dir[r][c] = 0;      //Check left and right neighbors
                } else if ((angle >= 22.5 && angle <= 67.5) || (angle >= 202.5 && angle <= 247.5)) {
                    dir[r][c] = 45;     //Check diagonal (upper right and lower left) neighbors
                } else if ((angle >= 67.5 && angle <= 112.5) || (angle >= 247.5 && angle <= 292.5)) {
                    dir[r][c] = 90;     //Check top and bottom neighbors
                } else {
                    dir[r][c] = 135;    //Check diagonal (upper left and lower right) neighbors
                }
            }
        }
    }
    
    /**
     * Call this method to use gradient direction and magnitude to suppress lesser pixels.
     * 
     * @return void
     */
    private static void Suppression() {
        int height = mag.length - 1;
        int width = mag[0].length - 1;
        
        for (int r = 1; r < height; r++) {
            for (int c = 1; c < width; c++) {
                double magnitude = mag[r][c];
                
                switch (dir[r][c]) {
                    case 0 :
                        if (magnitude < mag[r][c - 1] && magnitude < mag[r][c + 1]) {
                            mag [r - 1][c - 1] = 0;
                        }
                        break;
                    case 45 :
                        if (magnitude < mag[r - 1][c + 1] && magnitude < mag[r + 1][c - 1]) {
                            mag [r - 1][c - 1] = 0;
                        }
                        break;
                    case 90 :
                        if (magnitude < mag[r - 1][c] && magnitude < mag[r + 1][c]) {
                            mag [r - 1][c - 1] = 0;
                        }
                        break;
                    case 135 :
                        if (magnitude < mag[r - 1][c - 1] && magnitude < mag[r + 1][c + 1]) {
                            mag [r - 1][c - 1] = 0;
                        }
                        break;
                }
            }
        }
    }
    
    /**
     * Call this method to use an upper and lower threshold to decided which non-suppressed pixels are edges.
     * 
     * @return bin  int[][], the binary image showing edges in the original.
     */
    private static int[][] Hysteresis() {
        int height = mag.length - 1;
        int width = mag[0].length - 1;
        int[][] bin = new int[height - 1][width - 1];
        
        tHi = mean + (numDev * stDev);    //Magnitude greater than or equal to high threshold is an edge pixel
        tLo = tHi * tFract;               //Magnitude less than low threshold not an edge, equal or greater possible edge
        
        for (int r = 1; r < height; r++) {
            for (int c = 1; c < width; c++) {
                double magnitude = mag[r][c];
                
                if (magnitude >= tHi) {
                    bin[r - 1][c - 1] = 255;
                } else if (magnitude < tLo) {
                    bin[r - 1][c - 1] = 0;
                } else {    //This could be separate method or lambda
                    boolean connected = false;
                    
                    for (int nr = -1; nr < 2; nr++) {
                        for (int nc = -1; nc < 2; nc++) {
                            if (mag[r + nr][c + nc] >= tHi) {
                                connected = true;
                            }
                        }
                    }
                    
                    bin[r - 1][c - 1] = (connected) ? 255 : 0;
                }
            }
        }
        
        return bin;
    }
    
    /**
     * Send this method an array of grayscale pixels (int) to get a BufferedImage
     * 
     * @param raw   int[][] representing grayscale pixels of image.
     * @return img  BufferedImage built from grayscale array 
     */
    public static BufferedImage GSImg(int[][] raw) {
        BufferedImage img = null;
        int height = raw.length;
        int width = raw[0].length;
        
        if (height > 0 && width > 0) {
            img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    img.setRGB(j, i, (raw[i][j] << 16) | (raw[i][j] << 8) | (raw[i][j]));
                }
            }
        }
        
        return img;
    }
}
