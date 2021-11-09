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
 * @author dit,Diaz,Angga
 */
public class Aritmatika {
    public static void citraTambahImage(String nama_file1, String nama_file2, String tipe_file) {
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
                        Color c = new Color(image1.getRGB(j, i));
                        int red = (int) (c.getRed());
                        int green = (int) (c.getGreen());
                        int blue = (int) (c.getBlue());
                        int RGB = red + green + blue;
                        Color c2 = new Color(image2.getRGB(j, i));
                        int red2 = (int) (c2.getRed());
                        int green2 = (int) (c2.getGreen());
                        int blue2 = (int) (c2.getBlue());
                        int RGB2 = red2 + green2 + blue2;
                        int mixRed = (red + red2)/2;
                        int mixGreen = (green + green2)/2;
                        int mixBlue = (blue + blue2)/2;
                        if (RGB > RGB2)
                            image3.setRGB(j, i, new Color(mixRed, mixGreen, mixBlue).getRGB());
                        else
                            image3.setRGB(j, i, image1.getRGB(j, i));
                    }
                }

                String output_name = nama_file1 + "_" + nama_file2 + "_merged." + tipe_file;
                File output = new File(output_name);
                ImageIO.write(image3, tipe_file, output);
            } else {
                System.out.println("Maaf tidak bisa diproses karena ukuran gambar berbeda");
            }
        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }

    public static void citraTambahSkalar(String nama_file, String tipe_file, int x) {
        try {
            String input_name = nama_file + "." + tipe_file;
            File input = new File(input_name);
            BufferedImage image = ImageIO.read(input);
            int width = image.getWidth();
            int height = image.getHeight();

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int) (c.getRed())+x;
                    if (red < 0)red = 0;
                    else if (red > 255)red = 255;

                    int green = (int) (c.getGreen())+x;
                    if (green < 0)green = 0;
                    else if (green > 255)green = 255;

                    int blue = (int) (c.getBlue())+x;
                    if (blue < 0)blue = 0;
                    else if (blue > 255)blue = 255;

                    Color newColor = new Color(red, green, blue);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }

            String output_name = nama_file + "_TambahSkalar." + tipe_file;
            File output = new File(output_name);
            ImageIO.write(image, tipe_file, output);
        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }

    public static void citraKurangImage(String nama_file1, String nama_file2, String tipe_file) {
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
                        Color c = new Color(image1.getRGB(j, i));
                        int red = (int) (c.getRed());
                        int green = (int) (c.getGreen());
                        int blue = (int) (c.getBlue());
                        int RGB = red + green + blue;
                        Color c2 = new Color(image2.getRGB(j, i));
                        int red2 = (int) (c2.getRed());
                        int green2 = (int) (c2.getGreen());
                        int blue2 = (int) (c2.getBlue());
                        int RGB2 = red2 + green2 + blue2;
                        int mixRed = (red - red2)/2;
                        if(mixRed<0)mixRed = 0;
                        int mixGreen = (green - green2)/2;
                        if(mixGreen<0)mixGreen = 0;
                        int mixBlue = (blue - blue2)/2;
                        if(mixBlue<0)mixBlue = 0;
                        if (RGB == RGB2)
                            image3.setRGB(j, i, new Color(mixRed, mixGreen, mixBlue).getRGB());
                        else
                            image3.setRGB(j, i, image1.getRGB(j, i));
                    }
                }

                String output_name = nama_file1 + "_" + nama_file2 + "_altered." + tipe_file;
                File output = new File(output_name);
                ImageIO.write(image3, tipe_file, output);
            } else {
                System.out.println("Maaf tidak bisa diproses karena ukuran gambar berbeda");
            }
        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }

    public static void citraKurangSkalar(String nama_file, String tipe_file, int x) {
        try {
            String input_name = nama_file + "." + tipe_file;
            File input = new File(input_name);
            BufferedImage image = ImageIO.read(input);
            int width = image.getWidth();
            int height = image.getHeight();

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int) (c.getRed() - x);
                    if (red < 0)
                        red = 0;
                    else if (red > 255)
                        red = 255;

                    int green = (int) (c.getGreen() - x);
                    if (green < 0)
                        green = 0;
                    else if (green > 255)
                        green = 255;

                    int blue = (int) (c.getBlue() - x);
                    if (blue < 0)
                        blue = 0;
                    else if (blue > 255)
                        blue = 255;

                    Color newColor = new Color(red, green, blue);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }

            String output_name = nama_file + "_KurangSkalar." + tipe_file;
            File output = new File(output_name);
            ImageIO.write(image, tipe_file, output);
        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }

    public static void perkalian2Citra(String nama_file1, String nama_file2, String tipe_file) {
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
            if (width1 == height2) {
                BufferedImage image3 = new BufferedImage(width2, height1, BufferedImage.TYPE_INT_RGB);
                for (int i = 0; i < height1; i++) {
                    for (int j = 0; j < width2; j++) {
                        int tempRed = 0;
                        int tempGreen = 0;
                        int tempBlue = 0;
                        for (int k = 0; k < height2; k++) {
                            Color c = new Color(image1.getRGB(k, i));
                            int red = (int) (c.getRed());
                            int green = (int) (c.getGreen());
                            int blue = (int) (c.getBlue());
                            Color c2 = new Color(image2.getRGB(j, k));
                            int red2 = (int) (c2.getRed());
                            int green2 = (int) (c2.getGreen());
                            int blue2 = (int) (c2.getBlue());

                            tempRed = tempRed + (red * red2);
                            if (tempRed < 0)
                                tempRed = 0;
                            else if (tempRed > 255)
                                tempRed = 255;

                            tempGreen = tempGreen + (green * green2);
                            if (tempGreen < 0)
                                tempGreen = 0;
                            else if (tempGreen > 255)
                                tempGreen = 255;

                            tempBlue = tempBlue + (blue * blue2);
                            if (tempBlue < 0)
                                tempBlue = 0;
                            else if (tempBlue > 255)
                                tempBlue = 255;
                        }

                        image3.setRGB(j, i, new Color(tempRed, tempGreen, tempBlue).getRGB());
                    }
                    String output_name = nama_file1 + "_Kali_" + nama_file2 + "." + tipe_file;
                    File output = new File(output_name);
                    ImageIO.write(image3, tipe_file, output);
                }
            } else {
                System.out.println("Maaf tidak bisa diproses karena ukuran gambar berbeda");
            }
        } catch (

        IOException e) {
            System.out.println("Error = " + e);
        }
    }

    public static void perkalianCitraSkalar(String nama_file, String tipe_file, int k) {
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

                    int tempRed = red * k;
                    if (tempRed < 0)
                        tempRed = 0;
                    else if (tempRed > 255)
                        tempRed = 255;

                    int tempGreen = green * k;
                    if (tempGreen < 0)
                        tempGreen = 0;
                    else if (tempGreen > 255)
                        tempGreen = 255;

                    int tempBlue = blue * k;
                    if (tempBlue < 0)
                        tempBlue = 0;
                    else if (tempBlue > 255)
                        tempBlue = 255;

                    image2.setRGB(j, i, new Color(tempRed, tempGreen, tempBlue).getRGB());
                }
            }

            String output_name = nama_file + "_Kali" + k + "." + tipe_file;
            File output = new File(output_name);
            ImageIO.write(image2, tipe_file, output);

        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }

    public static void pembagianCitraSkalar(String nama_file, String tipe_file, int k) {
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

                    int tempRed = red / k;
                    if (tempRed < 0)
                        tempRed = 0;
                    else if (tempRed > 255)
                        tempRed = 255;

                    int tempGreen = green / k;
                    if (tempGreen < 0)
                        tempGreen = 0;
                    else if (tempGreen > 255)
                        tempGreen = 255;

                    int tempBlue = blue / k;
                    if (tempBlue < 0)
                        tempBlue = 0;
                    else if (tempBlue > 255)
                        tempBlue = 255;

                    image2.setRGB(j, i, new Color(tempRed, tempGreen, tempBlue).getRGB());
                }
            }

            String output_name = nama_file + "_Bagi" + k + "." + tipe_file;
            File output = new File(output_name);
            ImageIO.write(image2, tipe_file, output);

        } catch (IOException e) {
            System.out.println("Error = " + e);
        }
    }
}
