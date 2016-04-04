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
 *
 * @author Simo
 */
public class Mountains implements Terrain {

    private static BufferedImage image;
    
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
    public void draw(Graphics2D g2d, int x, int y) {
        g2d.drawImage(image, x, y, null);
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
