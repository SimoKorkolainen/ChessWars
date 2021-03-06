/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.terrain;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.util.ImageLoader;

/**
 * Woods on metsikköä kuvaava luokka.
 */
public class Woods implements Terrain {
    private static BufferedImage image;
    /**
     * Konstruktori luo metsikkömaaston ja lataa metsikköön liittyvän kuvan.
     */
    public Woods() {
        
        if (image == null) {
            ImageLoader loader = new ImageLoader();
            image = loader.loadImage("/images/Woods.png");
        }
    
    }

    
    @Override
    public BufferedImage getImage() {
        return image;
    }
    
    @Override
    public int moveCost() {
        return 2;
    }

    
    @Override
    public String getName() {
        return "Woods";
    }
    
    @Override
    public Terrain copy() {
        return new Woods();
    }
}
