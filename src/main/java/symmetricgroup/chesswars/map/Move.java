package symmetricgroup.chesswars.map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import symmetricgroup.chesswars.pieces.Piece;

/**
 *
 * @author simokork
 */
public class Move {
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private Piece piece;
    private Piece eaten;

    public Move(int startX, int startY, int endX, int endY, Piece piece, Piece eaten) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.piece = piece;
        this.eaten = eaten;
    }
    
    
}
