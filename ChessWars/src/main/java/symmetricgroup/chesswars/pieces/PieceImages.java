/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.pieces;

import java.awt.Color;
import java.awt.image.BufferedImage;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.util.ImageColorer;
import symmetricgroup.chesswars.util.ImageLoader;

/**
 * PieceImage-luokka sisältää nappulatyyppiin liittyvät kuvat.
 */
public class PieceImages {
    
    
    private BufferedImage whiteImage;
    private BufferedImage blackImage;
    private BufferedImage redImage;
    private BufferedImage greenImage;
    private BufferedImage blueImage;
    private BufferedImage yellowImage;
    
    public PieceImages(String pieceName) {
        
        String startPart = "/images/";
        String endPart = pieceName + ".png";
        ImageLoader loader = new ImageLoader();
        whiteImage = loader.loadImage(startPart + "White" + endPart);
        blackImage = loader.loadImage(startPart + "Black" + endPart);

        redImage = ImageColorer.color(whiteImage, ArmyColor.RED.getDrawingColor());
        greenImage = ImageColorer.color(whiteImage, ArmyColor.GREEN.getDrawingColor());
        blueImage = ImageColorer.color(whiteImage, ArmyColor.BLUE.getDrawingColor());
        yellowImage = ImageColorer.color(whiteImage, ArmyColor.YELLOW.getDrawingColor());
                
    }
    
    
    public BufferedImage getImage(ArmyColor color) {

        switch (color) {
            
            case RED: return redImage;
                
            case GREEN: return greenImage;
                
            case BLUE: return blueImage;
                
            case BLACK: return blackImage;
                
            case WHITE: return whiteImage;
            
            case YELLOW: return yellowImage;
                
            default: return null;
    
        }
        
    }
    
    
}
