/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.players.ai;

import java.util.Set;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.players.ArmyColor;

/**
 * AiEvaluator on luokka, jonka avulla lasketaan tekoälyn käyttämä heuristiikkafunktio kartan perusteella.
 */
public class AiEvaluator {
    public static final double ROOK_VALUE = 5;
    public static final double KNIGHT_VALUE = 3;
    public static final double BISHOP_VALUE = 3;
    public static final double KING_VALUE = 100000;
    public static final double QUEEN_VALUE = 9;
    public static final double PAWN_VALUE = 1;
    public static final double AGGRESSIVENESS = 0.2;
    public static final double RANDOMNESS = 0.001;
    public static final double DIST_TO_KING_IMPORTANCE = 0.01;
    
    
    /**
     * Metodi laskee heuristiikkafunktion arvon kartan nykyisen tilanteen perusteella.
     * @param map evaluoitava kartta
     * @param myTeam evaluoitavan pelaajan joukkue
     * @param random totuusarvo, joka määrittelee onko tulos satunnainen vai ei
     * @return heuristiikkafunktion arvo
     */
    public static double evaluate(BattleMap map, Set<ArmyColor> myTeam, boolean random) {
        double eval = addRandomness(random);
        
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                eval += evalMapPosition(i, j, map, myTeam);
            }
        }

        
        return eval;
    
    }
    
    private static double addRandomness(boolean random) {
        double eval = 0;
        if (random) {
            eval += Math.random() * RANDOMNESS;
        }
        return eval;
    }
    
    private static double evalMapPosition(int x, int y, BattleMap map, Set<ArmyColor> myTeam) {
        
        
        Piece p = map.getPiece(x, y);
        
        if (p != null) {


            return evaluatePiece(x, y, map, p, myTeam);
            
        }
        
        return 0;
    }
    
    private static double evaluatePiece(int x, int y, BattleMap map, Piece piece, Set<ArmyColor> myTeam) {
        double factor = -1 - AGGRESSIVENESS;

        if (myTeam.contains(piece.getColor())) {
            factor = 1;
        }

        if (!"King".equals(piece.getName()))  {
            return factor * evaluateNotKing(piece);
        }


        return evaluateKing(factor, map, myTeam, x, y);
    }
    
    private static double evaluateKing(double factor, BattleMap map, Set<ArmyColor> myTeam, int kingX, int kingY) {
        if (myTeam.contains(map.getPiece(kingX, kingY).getColor())) {
            return factor * KING_VALUE;
        } 
        return factor * KING_VALUE - distanceToKing(map, myTeam, kingX, kingY) * DIST_TO_KING_IMPORTANCE;
    }
    
    private static double evaluateNotKing(Piece piece) {
        switch (piece.getName()) {

            case "Rook": return ROOK_VALUE;
            case "Bishop": return BISHOP_VALUE;
            case "Knight": return KNIGHT_VALUE;  
            case "Queen": return QUEEN_VALUE;
            case "Pawn": return PAWN_VALUE; 
            default: return 0;
        }
    }
    
    
    private static double distanceToKing(BattleMap map, Set<ArmyColor> myTeam, int kingX, int kingY) {
        double distSum = 0;
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                
                distSum += pieceDistanceToKing(map, myTeam, kingX, kingY, i, j);
                
            }
        
        }
        return distSum;
    }
    
    private static double pieceDistanceToKing(BattleMap map, Set<ArmyColor> myTeam, int kingX, int kingY, int x, int y) {
        if (map.getPiece(x, y) != null) {
            if (myTeam.contains(map.getPiece(x, y).getColor())) {
                return distance(kingX, kingY, x, y) / (map.getWidth() + map.getHeight());
            }
        }
        return 0;
    }
    
    /**
     * Metodi laskee kahden tason pisteen a ja b välisen etäisyyden.
     * @param x pisteen a x-koordinaatti
     * @param y pisteen a y-koordinaatti
     * @param endX pisteen b x-koordinaatti
     * @param endY pisteen b y-koordinaatti
     * @return euklidinen etaisyys
     */
    public static double distance(int x, int y, int endX, int endY) {
        
        int dx = x - endX;
        int dy = y - endY;
        
        return Math.sqrt(dx * dx + dy * dy);
    
    }
}
