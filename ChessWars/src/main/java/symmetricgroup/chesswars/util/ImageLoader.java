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
 *
 * @author Simo
 */
public class ImageLoader {
    
    
    public static BufferedImage loadImage(String filename) {
        
        try {
            return ImageIO.read(new File(filename));
        
        } catch (Exception e) {
            
            System.out.println("Unable to read image " + filename + ": " + e.getMessage());
            
        }
        
        return null;
    }
    
    
}
