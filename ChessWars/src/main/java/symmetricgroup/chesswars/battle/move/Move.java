package symmetricgroup.chesswars.battle.move;

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

    /**
     * Konstruktori luo uuden siirron.
     * @param startX x-koordinaatti, josta siirto alkaa
     * @param startY y-koordinaatti, josta siirto alkaa
     * @param endX x-koordinaatti, johon siirto loppuu
     * @param endY y-koordinaatti, johon siirto loppuu
     * @param piece siirrettävä nappula
     * @param eaten syöty nappula
     */
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
    
    /**
     * Metodi palauttaa siirron kopion.
     * @return palauttaa siirron kopion
     */
    public Move copy() {
        
        return new Move(startX, startY, endX, endY, pieceCopy(piece), pieceCopy(eaten));
        
    }
    
    private Piece pieceCopy(Piece piece) {
        if (piece == null) {
            return null;
        }
        return piece.copy();
    }
    
    @Override
    public String toString() {
        return "" + startX + " " + startY + " " + endX + " " + endY + " " + piece + " " + eaten;
    }
    /**
     * Metodi luo uuden siirron tekstin perusteella.
     * @param moveConf siirron tiedot tekstinä
     * @return palauttaa luodun siirron
     */
    public static Move stringToMove(String moveConf) {
        
        String[] conf = moveConf.split(" ");
        
        try {

            return parseMove(conf);

        } catch (Exception e) { }
        
        return null;
    
    }
    
    private static Move parseMove(String[] conf) throws Exception {
        int sX = Integer.parseInt(conf[0]);
        int sY = Integer.parseInt(conf[1]);
        int eX = Integer.parseInt(conf[2]);
        int eY = Integer.parseInt(conf[3]);
        Piece p = PieceParser.stringToPiece(conf[4]);
        Piece e = PieceParser.stringToPiece(conf[5]);

        return new Move(sX, sY, eX, eY, p, e);
        
    }

    public void setEaten(Piece eaten) {
        this.eaten = eaten;
    }
    
    
}
