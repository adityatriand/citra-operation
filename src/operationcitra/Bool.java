/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operationcitra;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Adit,Diaz,Angga
 */
public class Bool {
    public static void citraAND(String nama_file1, String nama_file2, String tipe_file) {
        try {
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
            if (width1 == width2 && height1 == height2) {
                BufferedImage image3 = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);
                for (int i = 0; i < height1; i++) {
                    for (int j = 0; j < width1; j++) {
                        Color newColor;
                        Color c = new Color(image1.getRGB(j, i));
                        Color c2 = new Color(image2.getRGB(j, i));
                        int red = (int)((c.getRed())| c2.getRed());
                        int green = (int) ((c.getGreen())| c2.getGreen());
                        int blue = (int)(c.getBlue()| c2.getBlue());
                        newColor = new Color(red,green,blue);
                        image3.setRGB(j, i, newColor.getRGB());
                    }
                }
                String output_name = nama_file1 + "_AND_" + nama_file2 + "." + tipe_file;
                File output = new File(output_name);
                ImageIO.write(image3, tipe_file, output);
            } else {
                System.out.println("Maaf tidak bisa diproses karena ukuran gambar berbeda");
            }
        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }

    public static void citraOR(String nama_file1, String nama_file2, String tipe_file) {
        try {
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
            if (width1 == width2 && height1 == height2) {
                BufferedImage image3 = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);
                for (int i = 0; i < height1; i++) {
                    for (int j = 0; j < width1; j++) {
                        Color newColor;
                        Color c = new Color(image1.getRGB(j, i));
                        Color c2 = new Color(image2.getRGB(j, i));
                        int red = (int)((c.getRed())& c2.getRed());
                        int green = (int) ((c.getGreen())& c2.getGreen());
                        int blue = (int)(c.getBlue()& c2.getBlue());
                        newColor = new Color(red,green,blue);
                        image3.setRGB(j, i, newColor.getRGB());
                    }
                }
                String output_name = nama_file1 + "_OR_" + nama_file2 + "." + tipe_file;
                File output = new File(output_name);
                ImageIO.write(image3, tipe_file, output);
            } else {
                System.out.println("Maaf tidak bisa diproses karena ukuran gambar berbeda");
            }
        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }

    public static void citraXOR(String nama_file1, String nama_file2, String tipe_file) {
        try {
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
            if (width1 == width2 && height1 == height2) {
                BufferedImage image3 = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);
                for (int i = 0; i < height1; i++) {
                    for (int j = 0; j < width1; j++) {
                        Color newColor;
                        Color c = new Color(image1.getRGB(j, i));
                        Color c2 = new Color(image2.getRGB(j, i));
                        int red = (int)((c.getRed())^ c2.getRed());
                        int green = (int) ((c.getGreen())^ c2.getGreen());
                        int blue = (int)(c.getBlue()^ c2.getBlue());
                        newColor = new Color(red,green,blue);
                        image3.setRGB(j, i, newColor.getRGB());
                    }
                }
                String output_name = nama_file1 + "_XOR_" + nama_file2 + "." + tipe_file;
                File output = new File(output_name);
                ImageIO.write(image3, tipe_file, output);
            } else {
                System.out.println("Maaf tidak bisa diproses karena ukuran gambar berbeda");
            }
        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }

    public static void citraNOT(String nama_file, String tipe_file) {
        try {
            String input_name = nama_file + "." + tipe_file;
            File input = new File(input_name);
            BufferedImage image = ImageIO.read(input);
            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int) (c.getRed());
                    int green = (int) (c.getGreen());
                    int blue = (int) (c.getBlue());
                    int BW = (red + green + blue) / 3;
                    if (BW < 255)
                        BW = 255;
                    else
                        BW = 0;
                    Color newColor = new Color(BW, BW, BW);
                    image2.setRGB(j, i, newColor.getRGB());
                }
            }
            String output_name = nama_file + "_NOT." + tipe_file;
            File output = new File(output_name);
            ImageIO.write(image2, tipe_file, output);
        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }
}
