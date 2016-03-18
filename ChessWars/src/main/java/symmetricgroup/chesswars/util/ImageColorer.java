/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.util;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Simo
 */
public class ImageColorer {
    
    public static BufferedImage color(BufferedImage mask, Color color) {
        BufferedImage colored = new BufferedImage(mask.getWidth(), mask.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        
        for (int i = 0; i < mask.getWidth(); i++) {
            for (int j = 0; j < mask.getHeight(); j++) {
                int col = mask.getRGB(i, j);
                
                int alpha = (col & 0xff000000) >> 24;
                int r = (col & 0x00ff0000) >> 16;
                int g = (col & 0x0000ff00) >> 8;
                int b = (col & 0x000000ff);
                
                int newR = (int) Math.floor((double) r / 255 * color.getRed());
                int newG = (int) Math.floor((double) g / 255 * color.getGreen());
                int newB = (int) Math.floor((double) b / 255 * color.getBlue());
                
                int newCol = (alpha << 24) | (newR << 16) | (newG << 8) | newB;
                
                colored.setRGB(i, j, newCol);
            }
            
        }
        
    
        return colored;
    }
    
    

    
}
