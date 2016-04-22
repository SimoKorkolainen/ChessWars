/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.pieces;

import symmetricgroup.chesswars.players.ArmyColor;

/**
 * PieceParser-luokan avulla on mahdollista luoda uusi Piece-olio String-olion perusteella.
 */
public class PieceParser {
    
    /**
     * Metodi luo nappulan tekstin perusteella.
     * @param pieceConf nappulan tiedot tekstinä
     * @return tekstin perusteella luotu nappula
     */
    public static Piece stringToPiece(String pieceConf) {
        if ("null".equals(pieceConf)) {
            return null;
        }
        String[] conf = pieceConf.split("_");
        
        ArmyColor color = ArmyColor.stringToArmyColor(conf[0]);
        
        String pieceName = conf[1];
        
        return createPiece(pieceName, color);
    
    }
    
    private static Piece createPiece(String pieceName, ArmyColor color) {
        switch (pieceName) {
        
            case "Rook": return new Rook(color);
            case "Pawn": return new Pawn(color);
            case "Bishop": return new Bishop(color);
            case "King": return new King(color);
            case "Queen": return new Queen(color);
            case "Knight": return new Knight(color);
                
            default: return null;
        }
    }
}
