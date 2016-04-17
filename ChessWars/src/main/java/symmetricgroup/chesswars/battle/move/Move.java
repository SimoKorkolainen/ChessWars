package symmetricgroup.chesswars.battle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.pieces.PieceParser;

/**
 * Move on luokka, joka sisältää pelaajan siirron tiedot.
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

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public Piece getPiece() {
        return piece;
    }

    public Piece getEaten() {
        return eaten;
    }
    
    
    public Move copy() {
        return new Move(startX, startY, endX, endY, piece.copy(), eaten.copy());
    }
    
    @Override
    public String toString() {
        return "" + startX + " " + startY + " " + endX + " " + endY + " " + piece + " " + eaten;
    }
    
    public static Move stringToMove(String moveConf) {
        
        String[] conf = moveConf.split(" ");
        
        try {

            int startX = Integer.parseInt(conf[0]);
            int startY = Integer.parseInt(conf[1]);
            int endX = Integer.parseInt(conf[2]);
            int endY = Integer.parseInt(conf[3]);
            Piece piece = PieceParser.stringToPiece(conf[4]);
            Piece eaten = PieceParser.stringToPiece(conf[5]);
            
            return new Move(startX, startY, endX, endY, piece, eaten);
        
        } catch (Exception e) {
            System.out.println("Unable to parse move: " + e.getMessage());
        }
        
        return null;
    
    }
}