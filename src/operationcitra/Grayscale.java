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
public class Grayscale {
    public static void Operation(String nama_file, String tipe_file){
        try{
            String input_name = nama_file +"."+tipe_file;
            File input = new File(input_name);
            BufferedImage image = ImageIO.read(input);
            int width = image.getWidth();
            int height = image.getHeight();
            for(int i=0; i<height;i++){
                for(int j=0; j<width;j++){
                    Color c = new Color(image.getRGB(j,i));
                    int red = (int)(c.getRed()*0.299);
                    int green = (int)(c.getGreen()*0.587);
                    int blue = (int)(c.getBlue()*0.114);
                    Color newColor = new Color(red+green+blue,red+green+blue,red+green+blue);
                    image.setRGB(j,i,newColor.getRGB());
                }
            }
            String output_name = nama_file + "_grayscale."+tipe_file;
            File output = new File(output_name);
            ImageIO.write(image,tipe_file,output);
        }catch(IOException e){
            System.out.println("Error = "+e);
        }
    }  
}
