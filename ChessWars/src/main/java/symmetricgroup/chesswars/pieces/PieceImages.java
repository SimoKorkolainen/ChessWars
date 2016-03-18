/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.pieces;

import java.awt.Color;
import java.awt.image.BufferedImage;
import symmetricgroup.chesswars.map.ArmyColor;
import symmetricgroup.chesswars.util.ImageColorer;
import symmetricgroup.chesswars.util.ImageLoader;

/**
 *
 * @author Simo
 */
public class PieceImages {
    
    
    private BufferedImage whiteImage;
    private BufferedImage blackImage;
    private BufferedImage redImage;
    private BufferedImage greenImage;
    private BufferedImage blueImage;
    
    public PieceImages(String pieceName) {
        
        String startPart = "images/";
        String endPart = pieceName + ".png";
        
        whiteImage = ImageLoader.loadImage(startPart + "White" + endPart);
        blackImage = ImageLoader.loadImage(startPart + "Black" + endPart);
        
        redImage = ImageColorer.color(whiteImage, Color.red);
        greenImage = ImageColorer.color(whiteImage, Color.green);
        blueImage = ImageColorer.color(whiteImage, Color.blue);
        
        
    }
    
    
    public BufferedImage getImage(ArmyColor color) {

        switch (color) {
            
            case RED: return redImage;
                
            case GREEN: return greenImage;
                
            case BLUE: return blueImage;
                
            case BLACK: return blackImage;
                
            case WHITE: return whiteImage;
            
            default: return null;
    
        }
        
    }
    
    
}
