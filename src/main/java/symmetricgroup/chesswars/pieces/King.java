/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.pieces;

import symmetricgroup.chesswars.map.ArmyColor;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.map.Move;
import java.util.List;

/**
 *
 * @author simokork
 */
public class King extends Piece {
    
    static final int[] moveDirX = new int[]{1, 1, 0, -1, -1, -1, 0, 1};
    static final int[] moveDirY = new int[]{0, -1, -1, -1, 0, 1, 1, 1};
    static final boolean[] eatDir = new boolean[]{true, true, true, true, true, true, true, true};
     static final boolean[] mustEatDir = new boolean[]{false, false, false, false, false, false, false, false};
     
    public King(int x, int y, ArmyColor color) {
        super(x, y, color);
        
        setMoveLength(1);
        
        setMoveDirX(moveDirX);
        setMoveDirY(moveDirY);
        setEatDir(eatDir);
        setMustEatDir(mustEatDir);
        
        
        
    }

    
}
