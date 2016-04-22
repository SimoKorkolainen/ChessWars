/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.pieces;

import java.awt.image.BufferedImage;
import symmetricgroup.chesswars.players.ArmyColor;

/**
 * Rook on tornia kuvaava luokka.
 */
public class Rook extends Piece {
    private static PieceImages images;
    
    public static final int[] MOVE_DIR_X = new int[]{1, 0, -1, 0};
    public static final int[] MOVE_DIR_Y = new int[]{0, -1, 0, 1};
    public static final boolean[] EAT_DIR = new boolean[]{true, true, true, true};
    public static final boolean[] MUST_EAT_DIR = new boolean[]{false, false, false, false};
    
    /**
     * Konstruktori luo tornin.
     * @param color tornin väri
     */
    public Rook(ArmyColor color) {
        super(color, "Rook");
        
        setMoveLength(6);
        
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
        return new Rook(getColor());
    }
}
