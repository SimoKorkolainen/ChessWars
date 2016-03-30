/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.map;

import java.util.Set;
import symmetricgroup.chesswars.pieces.Piece;

/**
 *
 * @author Simo
 */
public class AiEvaluator {
    public static final double ROOK_VALUE = 5;
    public static final double KNIGHT_VALUE = 3;
    public static final double BISHOP_VALUE = 3;
    public static final double KING_VALUE = 100000;
    public static final double QUEEN_VALUE = 9;
    public static final double PAWN_VALUE = 1;
    public static double evaluate(BattleMap map, Set<ArmyColor> myTeam) {
        
        double eval = 0;
        
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                eval += evalMapPosition(i, j, map, myTeam);
            }
        }

        
        return eval;
    
    }
    
    private static double evalMapPosition(int x, int y, BattleMap map, Set<ArmyColor> myTeam) {
        double factor = -1;
        
        Piece p = map.getPiece(x, y);
        
        if (p != null) {
            if (myTeam.contains(p.getColor())) {
                factor = 1;
            }

            switch (p.getName()) {
            
                case "Rook": return factor * ROOK_VALUE;
                case "Bishop": return factor * BISHOP_VALUE;
                case "Knight": return factor * KNIGHT_VALUE;
                case "King": return factor * KING_VALUE;
                case "Queen": return factor * QUEEN_VALUE;
                case "Pawn": return factor * PAWN_VALUE; 
            }
        }
        
        return 0;
    }
}
