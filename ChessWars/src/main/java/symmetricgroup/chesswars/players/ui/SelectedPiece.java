/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.players.ui;

import symmetricgroup.chesswars.pieces.Piece;

/**
 * SelectedPiece on käyttäjän kartalta valitsema nappula.
 */
public class SelectedPiece {
    private Piece piece;
    private int x;
    private int y;

    public SelectedPiece(Piece piece, int x, int y) {
        this.piece = piece;
        this.x = x;
        this.y = y;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
}
