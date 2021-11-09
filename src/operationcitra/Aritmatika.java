/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operationcitra;

/**
 *
 * @author Adit,Diaz,Angga
 */
public class Aritmatika {
    public static void citraTambahImage (String nama_file1, String nama_file2, String tipe_file) {
        try{
            String input_name1 = nama_file1 + "." + tipe_file;
            String input_name2 = nama_file2 + "." + tipe_file;
            File input1 = new File(input_name1);
            File input2 = new File(input_name2);
            BufferedImage image1 = ImageIO.read(input1);
            BufferedImage image2 = ImageIO.read(input2);
            int width1 = image1.getWidth();
            int height1 = image1.getHeight();
            int width2 = image2.getWidth();
            int height2 = image2.getHeight();

            if(width1 == width2 && height1 == height2){
                BufferedImage image3 = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);

                for(int i = 0; i < height1; i++){
                    for(int j = 0; j < width1; j++){
                        Color c = new Color(image1.getRGB(j, i));
                        int red = (int)(c.getRed());
                        int green = (int)(c.getGreen());
                        int blue = (int)(c.getBlue());
                        int RGB = (red+green+blue)/3;

                        Color c2 = new Color(image2.getRGB(j,i));
                        int red2 = (int)(c2.getRed());
                        int green2 = (int)(c2.getGreen());
                        int blue2 = (int)(c2.getBlue());
                        int RGB2 = (red2+green2+blue2)/3;

                        int temp = RGB + RGB2;
                        if(temp > 255)image3.setRGB(j, i, new Color(255,255,255).getRGB());
                        else image3.setRGB(j, i,new Color(0,0,0).getRGB());
                    }
                }

                String output_name = nama_file1 + "_" + nama_file2 + "_merged." + tipe_file;
                File output = new File(output_name);
                ImageIO.write(image3, tipe_file, output);
            } else {
                System.out.println("Maaf tidak bisa diproses karena ukuran gambar berbeda");
            }
        } catch (IOException e){
            System.out.println("Error = " + e);
        }
    }

    public static void citraTambahSkalar (String nama_file1, String tipe_file, int x) {
        try{
            String input_name = nama_file1 + "." + tipe_file;
            File input = new File(input_name1);
            BufferedImage image = ImageIO.read(input1);
            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    Color c = new Color(image1.getRGB(j, i));
                    int red = (int) (c.getRed() + x);
                    if (red < 0) red = 0;
                    else if (red > 255) red = 255;

                    int green = (int) (c.getGreen() + x);
                    if (green < 0) green = 0;
                    else if (green > 255) green = 255;

                    int blue = (int) (c.getBlue() + x);
                    if (blue < 0) blue = 0;
                    else if (blue > 255) blue = 255;

                    Color newColor = new Color(red, green, blue);
                    image.setRGB(j, i, newColor.getRGB());
                    }
                }

            String output_name = nama_file1 + "_TambahSkalar." + tipe_file;
            File output = new File(output_name);
            ImageIO.write(image2, tipe_file, output);
        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }

    public static void citraKurangImage (String nama_file1, String nama_file2, String tipe_file) {
        try{
            String input_name1 = nama_file1 + "." + tipe_file;
            String input_name2 = nama_file2 + "." + tipe_file;
            File input1 = new File(input_name1);
            File input2 = new File(input_name2);
            BufferedImage image1 = ImageIO.read(input1);
            BufferedImage image2 = ImageIO.read(input2);
            int width1 = image1.getWidth();
            int height1 = image1.getHeight();
            int width2 = image2.getWidth();
            int height2 = image2.getHeight();

            if(width1 == width2 && height1 == height2){
                BufferedImage image3 = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);

                for(int i = 0; i < height1; i++){
                    for(int j = 0; j < width1; j++){
                        Color c = new Color(image1.getRGB(j, i));
                        int red = (int)(c.getRed());
                        int green = (int)(c.getGreen());
                        int blue = (int)(c.getBlue());
                        int RGB = (red+green+blue)/3;

                        Color c2 = new Color(image2.getRGB(j,i));
                        int red2 = (int)(c2.getRed());
                        int green2 = (int)(c2.getGreen());
                        int blue2 = (int)(c2.getBlue());
                        int RGB2 = (red2+green2+blue2)/3;

                        int temp = RGB + RGB2;
                        if(temp != 0)image3.setRGB(j, i, new Color(255,255,255).getRGB());
                        else image3.setRGB(j, i,new Color(0,0,0).getRGB());
                    }
                }

                String output_name = nama_file1 + "_" + nama_file2 + "_altered." + tipe_file;
                File output = new File(output_name);
                ImageIO.write(image3, tipe_file, output);
            } else {
                System.out.println("Maaf tidak bisa diproses karena ukuran gambar berbeda");
            }
        } catch (IOException e){
            System.out.println("Error = " + e);
        }
    }

    public static void citraKurangSkalar (String nama_file1, String tipe_file, int x) {
        try{
            String input_name = nama_file1 + "." + tipe_file;
            File input = new File(input_name1);
            BufferedImage image = ImageIO.read(input1);
            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    Color c = new Color(image1.getRGB(j, i));
                    int red = (int) (c.getRed() - x);
                    if (red < 0) red = 0;
                    else if (red > 255) red = 255;

                    int green = (int) (c.getGreen() - x);
                    if (green < 0) green = 0;
                    else if (green > 255) green = 255;

                    int blue = (int) (c.getBlue() - x);
                    if (blue < 0) blue = 0;
                    else if (blue > 255) blue = 255;

                    Color newColor = new Color(red, green, blue);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }

            String output_name = nama_file1 + "_KurangSkalar." + tipe_file;
            File output = new File(output_name);
            ImageIO.write(image2, tipe_file, output);
        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }
}
