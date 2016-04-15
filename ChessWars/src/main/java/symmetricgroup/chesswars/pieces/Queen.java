/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.pieces;

import java.awt.image.BufferedImage;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.battle.Move;
import java.util.List;

/**
 * Queen on kuningatarta kuvaava luokka.
 */
public class Queen extends Piece {
    private static PieceImages images;
    
    
    public Queen(ArmyColor color) {
        super(color, "Queen");
        
        setMoveLength(6);
        
        setMoveDirX(King.MOVE_DIR_X);
        setMoveDirY(King.MOVE_DIR_Y);
        setEatDir(King.EAT_DIR);
        setMustEatDir(King.MUST_EAT_DIR);
        
        if (images == null) {
            images = new PieceImages(getName());
        }
    }

    @Override
    public BufferedImage getImage() {
        return images.getImage(getColor());
    }
    @Override
    public Piece copy() {
        return new Queen(getColor());
    }
}
