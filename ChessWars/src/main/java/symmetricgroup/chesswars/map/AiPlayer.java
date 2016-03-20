/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.map;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Simo
 */
public class AiPlayer {
    private int searchDepth;
    private ArmyColor color;
    private Set<ArmyColor> myTeam;
    public AiPlayer(int searchDepth, ArmyColor color) {
        this.searchDepth = searchDepth;
        this.color = color;
        this.myTeam = new HashSet<>();
        this.myTeam.add(color);
    }
    
    
    //Tekoäly pelaa aivan kuin kaikki eri tiimeissä olevat vastustajat olisivat liittoutuneet keskenään ja omien tiimiläisten siirrot olisivat tekoälyn itse päätettävissä.
    public Move alphaBeta(BattleMap map, int depth) {
        double value = -1000000;
        Move best = null;

        for (Move i : map.allPossibleNextMoves()) {
            
            map.doMove(i);
            
            double newValue;
            if (myTeam.contains(map.nextColorToMove())) {
                
                newValue = maxValue(map, -1000000, 1000000, depth - 1);
            } else {
                newValue = minValue(map, -1000000, 1000000, depth - 1);
            }
            
            if (newValue > value) {
                value = newValue;
                best = i;
            }
            
            map.undoLastMove();
        
        }
        System.out.println(value);
        return best;
    }
    
    public double maxValue(BattleMap map, double alpha, double beta, int depth) {
        //System.out.println("alphaBeta depth: " + depth + " turn: " + map.nextColorToMove());
        if (depth == 0) {
            return AiEvaluator.evaluate(map, myTeam);
        }
        
        double value = -1000000;
        
        for (Move i : map.allPossibleNextMoves()) {

            map.doMove(i);
            
            if (myTeam.contains(map.nextColorToMove())) {
                value = Math.max(value, maxValue(map, alpha, beta, depth - 1));
            } else {
                value = Math.max(value, minValue(map, alpha, beta, depth - 1));
            }
            
            if (beta <= value) {
                map.undoLastMove();
                return value;
            }
            
            alpha = Math.max(alpha, value);
            
            map.undoLastMove();
        
        }
        
        return value;
    
    }
    
    public double minValue(BattleMap map, double alpha, double beta, int depth) {
        
        if (depth == 0) {
            return AiEvaluator.evaluate(map, myTeam);
        }
        double value = 1000000;
        
        for (Move i : map.allPossibleNextMoves()) {
            
            map.doMove(i);
            
            if (myTeam.contains(map.nextColorToMove())) {
                value = Math.min(value, maxValue(map, alpha, beta, depth - 1));
            } else {
                value = Math.min(value, minValue(map, alpha, beta, depth - 1));
            }
            
            if (alpha >= value) {
                map.undoLastMove();
                return value;
            }
            
            beta = Math.min(beta, value);
            
            map.undoLastMove();
        
        }
        return value;
    }
    
}
