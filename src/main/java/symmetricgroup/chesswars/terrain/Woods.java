/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.terrain;

/**
 *
 * @author Simo
 */
public class Woods implements Terrain {

    @Override
    public int moveCost() {
        return 2;
    }
    
}
