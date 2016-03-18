/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.terrain;

import java.awt.Graphics2D;
import static symmetricgroup.chesswars.terrain.Mountains.getImage;

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
    
}
