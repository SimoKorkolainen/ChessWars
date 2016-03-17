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
public class Queen extends Piece {

    public Queen(ArmyColor color) {
        super(color);
        
        setMoveLength(6);
        
        setMoveDirX(King.moveDirX);
        setMoveDirY(King.moveDirY);
        setEatDir(King.eatDir);
        setMustEatDir(King.mustEatDir);
    }


    
}
