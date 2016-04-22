/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.terrain;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import symmetricgroup.chesswars.util.ImageLoader;

/**
 * Mountains on vuoristoa kuvaava luokka.
 */
public class Mountains implements Terrain {

    private static BufferedImage image;
    
    /**
     * Konstruktori luo vuoristomaaston ja lataa vuoristoon liittyvän kuvan.
     */
    public Mountains() {
        if (image == null) {
            ImageLoader loader = new ImageLoader();
            image = loader.loadImage("/images/Mountains.png");
        }
    }
    
    
    @Override
    public int moveCost() {
        return 6;
    }

    public BufferedImage getImage() {
        return image;
    }
    
    
    @Override
    public String getName() {
        return "Mountains";
    }
    
    @Override
    public Terrain copy() {
        return new Mountains();
    }
}
