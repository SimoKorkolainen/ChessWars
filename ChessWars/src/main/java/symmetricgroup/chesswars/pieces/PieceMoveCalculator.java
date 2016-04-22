/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.pieces;

import java.util.ArrayList;
import java.util.List;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.terrain.Mountains;
import symmetricgroup.chesswars.terrain.Terrain;



/**
 * PieceMoveCalculator laskee nappula siirrot.
 * @author Simo
 */
public class PieceMoveCalculator {
        
    
    /**
     * Metodi palauttaa siirrot, jotka nappulan on mahdollista tehdä taistelun kartalla.
     * Pelaajien vuoroilla ei ole vaikutusta metodin toimintaan.
     * @param piece nappula, jonka siirrot halutaan laskea
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @param battle taistelu, jossa siirrot halutaan laskea
     * @return kaikki mahdolliset siirrot
     */
    public static List<Move> getMoves(Piece piece, int x, int y, Battle battle) {
        
        int moveDirX[] = piece.getMoveDirX();
        int moveDirY[] = piece.getMoveDirY();
        int moveLength = piece.getMoveLength();
        boolean eatDir[] = piece.getEatDir();
        boolean mustEatDir[] = piece.getMustEatDir();
        
        List<Move> moves = new ArrayList<>();
        BattleMap map = battle.getMap();
        
        for (int i = 0; i < moveDirX.length; i++) {
            int stepsLeft = moveLength;
            for (int j = 1; j <= moveLength; j++) {
                
                int newX = x + j * moveDirX[i];
                int newY = y + j * moveDirY[i];
                
                if (!map.isInside(newX, newY)) {
                    break;
                
                }
                
                Terrain terrain = map.getTerrain(newX, newY);
                
                if (terrain.getClass() == Mountains.class) {
                    if (!piece.getName().equals("Pawn")) {
                        break;
                    }
                }
                
                stepsLeft -= terrain.moveCost();
                
                if (stepsLeft < 0 && j > 1) {
                    break;
                }
                
                Piece eaten = map.getPiece(newX, newY);
                if (eaten != null) {
                    if (eatDir[i] && battle.getTeam(eaten.getColor()) != battle.getTeam(piece.getColor())) {
                        
                        boolean eat = !lastEatenWasOwnPawn(piece, battle) ||
                                                 lastEaterWasPawn(battle) ||
                                                 !isPawn(piece); 
                        
                        if (eat) {
                            moves.add(new Move(x, y, newX, newY, piece, eaten));
                        }
                        
                    }
                    
                    break;
                }
                if (!mustEatDir[i]) {
                    Move move = new Move(x, y, newX, newY, piece, null);
                    moves.add(move);
                    

                }
                
            }
        
        }
        
        return moves;
    }
    

    
    private static boolean isPawn(Piece piece) {
        return "Pawn".equals(piece.getName());
    }

    private static boolean lastEaterWasPawn(Battle battle) {
        if (battle.getMoves().isEmpty()) {
            return false;
        }
        
        Move last = battle.getLastMove();
        Piece eater = last.getPiece();
        return isPawn(eater);
    }

    private static boolean lastEatenWasOwnPawn(Piece piece, Battle battle) {
        if (battle.getMoves().isEmpty()) {
            return false;
        }
        
        Move last = battle.getLastMove();
        Piece eaten = last.getEaten();
        if (eaten == null) {
            return false;
        }
        
        if (!isPawn(eaten)) {
            return false;
        }
        
        return eaten.getColor() == piece.getColor();
        
    }
    
    
}
