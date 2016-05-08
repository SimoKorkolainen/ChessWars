/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.pieces;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
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
    private Map<ArmyColor, BufferedImage> images;
    /**
     * Konstruktori lataa nappulan kuvat ja värittää ne.
     * @param pieceName nappulan nimi
     */
    public PieceImages(String pieceName) {
        this.images = new HashMap<>();
        String startPart = "/images/";
        String endPart = pieceName + ".png";
        ImageLoader loader = new ImageLoader();
        
        images.put(ArmyColor.WHITE, loader.loadImage(startPart + "White" + endPart));
        images.put(ArmyColor.BLACK, loader.loadImage(startPart + "Black" + endPart));

        addColoredImage(ArmyColor.RED);
        addColoredImage(ArmyColor.GREEN);
        addColoredImage(ArmyColor.BLUE);
        addColoredImage(ArmyColor.YELLOW);
                
    }
    
    private void addColoredImage(ArmyColor color) {
        images.put(color, ImageColorer.color(images.get(ArmyColor.WHITE), color.getDrawingColor()));
    }
    
    /**
     * Metodi palauttaa väriä vastaavan kuvan.
     * @param color väri, jonka kuva halutaan
     * @return väriä vastaavan kuvan
     */
    public BufferedImage getImage(ArmyColor color) {

        return images.get(color);
        
    }
    
    
}
