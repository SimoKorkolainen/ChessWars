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
    public static final double RANDOMNESS = 0.8;
    public static final double DIST_TO_KING_IMPORTANCE = 2;
    public static double evaluate(BattleMap map, Set<ArmyColor> myTeam, boolean random) {
        double eval = 0;
        
        if (random) {
            eval += Math.random() * RANDOMNESS;
        }
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                eval += evalMapPosition(i, j, map, myTeam);
            }
        }

        
        return eval;
    
    }
    
    private static double evalMapPosition(int x, int y, BattleMap map, Set<ArmyColor> myTeam) {
        double factor = -1 - AGGRESSIVENESS;
        
        Piece p = map.getPiece(x, y);
        
        if (p != null) {
            if (myTeam.contains(p.getColor())) {
                factor = 1;
            }

            switch (p.getName()) {
            
                case "Rook": return factor * ROOK_VALUE;
                case "Bishop": return factor * BISHOP_VALUE;
                case "Knight": return factor * KNIGHT_VALUE;
                       
                case "King": 
                    
                    if (myTeam.contains(p.getColor())) {
                        return factor * KING_VALUE;
                    } 
                    return factor * KING_VALUE - distanceToKing(map, myTeam, x, y) * DIST_TO_KING_IMPORTANCE;
                    
                case "Queen": return factor * QUEEN_VALUE;
                case "Pawn": return factor * PAWN_VALUE; 
            }
        }
        
        return 0;
    }
    
    
    public static double distanceToKing(BattleMap map, Set<ArmyColor> myTeam, int kingX, int kingY) {
        double distSum = 0;
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getPiece(i, j) != null) {
                    if (myTeam.contains(map.getPiece(i, j).getColor())) {
                        distSum += distance(kingX, kingY, i, j) / (map.getWidth() + map.getHeight());
                    }
                }
                
            }
        
        }
        return distSum;
    }
    
    public static double distance(int x, int y, int endX, int endY) {
        
        int dx = x - endX;
        int dy = y - endY;
        
        return Math.sqrt(dx * dx + dy * dy);
    
    }
}
