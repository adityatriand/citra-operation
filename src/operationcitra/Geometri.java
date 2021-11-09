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
public class Geometri {
    public static void citraTranslasi(String nama_file, String tipe_file, int x) {
        try {
            String input_name = nama_file + "." + tipe_file;
            File input = new File(input_name);
            BufferedImage image = ImageIO.read(input);
            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            int height2 = image2.getHeight();
            int width2 = image2.getWidth();
            int k = 0;
            for (int i = 0; i < height2; i++) {
                int l = 0;
                for (int j = 0; j < width2; j++) {
                    if (i < x || j < x) {
                        Color c = new Color(0, 0, 0);
                        image2.setRGB(j, i, c.getRGB());
                    } else {
                        Color c = new Color(image.getRGB(l, k));
                        image2.setRGB(j, i, c.getRGB());
                        l++;
                        if (l >= (width - (x)))
                            k++;
                    }
                }
            }

            String output_name = nama_file + "_Translasi." + tipe_file;
            File output = new File(output_name);
            ImageIO.write(image2, tipe_file, output);
        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }

    public static void citraRotasi90CW(String nama_file, String nama_file2, String tipe_file) {
        try {
            String input_name = nama_file + "." + tipe_file;
            File input = new File(input_name);
            BufferedImage image = ImageIO.read(input);
            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            int k = width - 1;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int p = image.getRGB(j, i);
                    image2.setRGB(k, j, p);
                }
                k--;
            }
            String output_name = nama_file + "_Rotasi90CW." + tipe_file;
            File output = new File(output_name);
            ImageIO.write(image2, tipe_file, output);
        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }

    public static void citraRotasi90CCW(String nama_file, String tipe_file) {
        try {
            String input_name = nama_file + "." + tipe_file;
            File input = new File(input_name);
            BufferedImage image = ImageIO.read(input);
            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < height; i++) {
                int k = width - 1;
                for (int j = 0; j < width; j++) {
                    int p = image.getRGB(j, i);
                    image2.setRGB(i, k, p);
                    k--;
                }
            }
            String output_name = nama_file + "_Rotasi90CCW." + tipe_file;
            File output = new File(output_name);
            ImageIO.write(image2, tipe_file, output);
        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }

    public static void citraHorizontalFlip(String nama_file, String tipe_file) {
        try {
            String input_name = nama_file + "." + tipe_file;
            File input = new File(input_name);
            BufferedImage image = ImageIO.read(input);
            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int k = width - 1;
                    int p = image.getRGB(j, i);
                    image2.setRGB(k, i, p);
                    k--;
                }
            }

            String output_name = nama_file + "_HorizontalFlip." + tipe_file;
            File output = new File(output_name);
            ImageIO.write(image2, tipe_file, output);
        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }

    public static void citraVerticalFlip(String nama_file, String tipe_file) {
        try {
            String input_name = nama_file + "." + tipe_file;
            File input = new File(input_name);
            BufferedImage image = ImageIO.read(input);
            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            int k = width - 1;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int p = image.getRGB(j, k);
                    image2.setRGB(j, i, p);
                }
                k--;
            }

            String output_name = nama_file + "_VerticalFlip." + tipe_file;
            File output = new File(output_name);
            ImageIO.write(image2, tipe_file, output);
        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }
}
