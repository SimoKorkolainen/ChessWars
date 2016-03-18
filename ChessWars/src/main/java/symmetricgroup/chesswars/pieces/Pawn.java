/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.pieces;

import java.awt.image.BufferedImage;
import symmetricgroup.chesswars.map.ArmyColor;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.map.Move;
import java.util.List;

/**
 *
 * @author simokork
 */
public class Pawn extends Piece {
    private static PieceImages images;
    
    static final int[] moveDirX = new int[]{1, 1, 0, -1, -1, -1, 0, 1};
    static final int[] moveDirY = new int[]{0, -1, -1, -1, 0, 1, 1, 1};
    static final boolean[] eatDir = new boolean[]{false, true, false, true, false, true, false, true};
    static final boolean[] mustEatDir = new boolean[]{false, true, false, true, false, true, false, true};
    
    public Pawn(ArmyColor color) {
        super(color, "Pawn");
        
        setMoveLength(1);
        
        setMoveDirX(moveDirX);
        setMoveDirY(moveDirY);
        setEatDir(eatDir);
        setMustEatDir(mustEatDir);
        
        if (images == null) {
            images = new PieceImages(getName());
        }
    }

    @Override
    public BufferedImage getImage() {
        return images.getImage(getColor());
    }
    
    
}
