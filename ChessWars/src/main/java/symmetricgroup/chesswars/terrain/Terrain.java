/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.terrain;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Terrain on käyttöliittymä, jonka maaston tulee toteuttaa.
 */
public interface Terrain {
        
    /**
     * 
     * @return palauttaa maaston läpikulkemisen kustannuksen
     */
    public int moveCost();

    /**
     * 
     * @return palauttaa maaston kuvan 
     */
    public BufferedImage getImage();
    
    /**
     * 
     * @return palauttaa maaston nimen
     */
    public String getName();
    
    /**
     * 
     * @return palauttaa kopion maastosta
     */
    public Terrain copy();
}
