/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.pieces;

import symmetricgroup.chesswars.map.ArmyColor;

/**
 *
 * @author Simo
 */
public class Bishop extends Piece {
    
    static final int[] moveDirX = new int[]{1, -1, -1, 1};
    static final int[] moveDirY = new int[]{-1, -1, 1, 1};
    static final boolean[] eatDir = new boolean[]{true, true, true, true};
    static final boolean[] mustEatDir = new boolean[]{false, false, false, false};
     

    public Bishop(ArmyColor color) {
        super(color);
        
        setMoveLength(6);
        
        setMoveDirX(moveDirX);
        setMoveDirY(moveDirY);
        setEatDir(eatDir);
        setMustEatDir(mustEatDir);
    }
    

}
