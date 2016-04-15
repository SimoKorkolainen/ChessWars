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
import symmetricgroup.chesswars.pieces.Piece;
import java.util.ArrayList;
import java.util.List;

/**
 * Knight-luokka kuvaa hevosta.
 */
public class Knight extends Piece {
    
    
    private static PieceImages images;
    
    private static final int[] MOVE_DIR_X = new int[]{2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] MOVE_DIR_Y = new int[]{-1, -2, -2, -1, 1, 2, 2, 1};
    private static final boolean[] EAT_DIR = new boolean[]{true, true, true, true, true, true, true, true};
    private static final boolean[] MUST_EAT_DIR = new boolean[]{false, false, false, false, false, false, false, false};
    
    public Knight(ArmyColor color) {
        super(color, "Knight");
        
        setMoveLength(1);
        
        setMoveDirX(MOVE_DIR_X);
        setMoveDirY(MOVE_DIR_Y);
        setEatDir(EAT_DIR);
        setMustEatDir(MUST_EAT_DIR);
        
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
        return new Knight(getColor());
    }
    
    
}
