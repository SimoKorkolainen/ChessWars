/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * ImageLoader on kuvien lukemiseen käytetty luokka.
 */
public class ImageLoader {
    
    
    public BufferedImage loadImage(String filename) {
        
        try {
            return ImageIO.read(getClass().getResource(filename));
        } catch (Exception e) {
            
            System.out.println("Unable to read image " + filename + ": " + e.getMessage());
            
        }
        
        return null;
    }
    
    
}
