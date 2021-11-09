/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edgedetection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Adit,Diaz,Angga
 */
public class Sobel {
    public static void main(String[] args) {
        File file = new File("gbr2.jpg");
        try {
            BufferedImage input = ImageIO.read(file);
            BufferedImage output = Edge.SobelEdgesVertikal(input);
            File out = new File("gbr2sobelhr.jpg");
            ImageIO.write(output, "jpg", out);
        } catch (IOException ex) {
            System.out.println("ERROR ACCESING IMAGE FILE:\n" + ex.getMessage());
        }
    } 
}
