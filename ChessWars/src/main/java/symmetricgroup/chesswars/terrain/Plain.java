/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.terrain;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Simo
 */
public class Plain implements Terrain {

    @Override
    public int moveCost() {
        return 1;
    }
    
    @Override
    public void draw(Graphics2D g2d, int x, int y) {
    
    }

    @Override
    public BufferedImage getImage() {
        return new BufferedImage(50, 50, BufferedImage.TYPE_4BYTE_ABGR_PRE);
    }
    
    
    
}
