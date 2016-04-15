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
        
    public int moveCost();
    
    public void draw(Graphics2D g2d, int x, int y);
    
    public BufferedImage getImage();
    
    public String getName();
    
    public Terrain copy();
}
