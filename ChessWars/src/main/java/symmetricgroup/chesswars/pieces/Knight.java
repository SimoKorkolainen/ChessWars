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
import symmetricgroup.chesswars.pieces.Piece;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simokork
 */
public class Knight extends Piece {
    
    
    private static PieceImages images;
    
    static final int[] moveDirX = new int[]{2, 1, -1, -2, -2, -1, 1, 2};
    static final int[] moveDirY = new int[]{-1, -2, -2, -1, 1, 2, 2, 1};
    static final boolean[] eatDir = new boolean[]{true, true, true, true, true, true, true, true};
    static final boolean[] mustEatDir = new boolean[]{false, false, false, false, false, false, false, false};
    
    public Knight(ArmyColor color) {
        super(color, "Knight");
        
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
