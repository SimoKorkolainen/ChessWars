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
public class Queen extends Piece {
    private static PieceImages images;
    
    
    public Queen(ArmyColor color) {
        super(color, "Queen");
        
        setMoveLength(6);
        
        setMoveDirX(King.moveDirX);
        setMoveDirY(King.moveDirY);
        setEatDir(King.eatDir);
        setMustEatDir(King.mustEatDir);
        
        if (images == null) {
            images = new PieceImages(getName());
        }
    }

    @Override
    public BufferedImage getImage() {
        return images.getImage(getColor());
    }
    
}
