/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.util;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * ImageColorer on kuvien värittämiseen käytetty luokka.
 */
public class ImageColorer {
    
    public static BufferedImage color(BufferedImage mask, Color color) {
        
        BufferedImage colored = new BufferedImage(mask.getWidth(), mask.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        
        for (int i = 0; i < mask.getWidth(); i++) {
            for (int j = 0; j < mask.getHeight(); j++) {
                
                int oldCol = mask.getRGB(i, j);
                
                colored.setRGB(i, j, getColoredColor(oldCol, color));
            }
            
        }
        
    
        return colored;
    }
    private static int getAlpha(int col) {
        return (col & 0xff000000) >> 24;
    }
    private static int getRed(int col) {
        return (col & 0x00ff0000) >> 16;
    }
    private static int getGreen(int col) {
        return (col & 0x0000ff00) >> 8;
    }
    private static int getBlue(int col) {
        return col & 0x000000ff;
    }
    
    private static int getColoredRed(int col, Color color) {
        return (int) Math.floor(getRed(col) / 255.0 * color.getRed());
    }
    
    private static int getColoredGreen(int col, Color color) {
        return (int) Math.floor(getGreen(col) / 255.0 * color.getGreen());
    }
    
    private static int getColoredBlue(int col, Color color) {
        return (int) Math.floor(getBlue(col) / 255.0 * color.getBlue());
    }
    
    private static int getColoredColor(int oldCol, Color color) {
        int newA = getAlpha(oldCol);
        int newR = getColoredRed(oldCol, color);
        int newG = getColoredGreen(oldCol, color);
        int newB = getColoredBlue(oldCol, color);

        return (newA << 24) | (newR << 16) | (newG << 8) | newB;
                
    }
    

}
