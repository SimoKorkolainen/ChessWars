/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.players.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.battle.BattleCopier;
import symmetricgroup.chesswars.battle.Move;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;

/**
 *
 * @author Simo
 */
public class AiPlayer implements Player {
    private int searchDepth;
    private ArmyColor color;
    private Set<ArmyColor> myTeam; //Koodia voi muokata siten, että myTeam on tarpeeton
    private Battle battle;
    private Battle copy;
    private boolean moveIsReady;
    private Move nextMove;

    public AiPlayer(int searchDepth, ArmyColor color, Battle battle) {
        this.searchDepth = searchDepth;
        this.color = color;
        this.myTeam = new HashSet<>();
        this.myTeam.add(color);
        this.searchDepth = searchDepth;
        this.battle = battle;
    }
    
    public void setUpTeam() {
        for (Player i : battle.getPlayers()) {
            if (battle.getTeam(color) == battle.getTeam(i.getColor())) {
                myTeam.add(i.getColor());
            }
        }
    }
    
    
    //Tekoäly pelaa aivan kuin kaikki eri tiimeissä olevat vastustajat olisivat liittoutuneet keskenään ja omien tiimiläisten siirrot olisivat tekoälyn itse päätettävissä.
    public Move alphaBeta() {
        copy = BattleCopier.copy(battle);
        double value = -1000000;
        Move best = null;
        
        List<EvalMove> evalMoves = sortedEvalMoves();
        Collections.reverse(evalMoves);
        for (EvalMove i : evalMoves) {
            
            copy.doMove(i.getMove());
            
            double newValue;
            if (myTeam.contains(copy.nextColorToMove())) {
                
                newValue = maxValue(-1000000, 1000000, searchDepth - 1);
            } else {
                newValue = minValue(-1000000, 1000000, searchDepth - 1);
            }
            
            if (newValue > value) {
                value = newValue;
                best = i.getMove();
            }
            
            copy.undoLastMove();
        
        }
        System.out.println(value);
        return best;
    }
    
    public double maxValue(double alpha, double beta, int depth) {
        //System.out.println("alphaBeta depth: " + depth + " turn: " + map.nextColorToMove());
        if (depth == 0) {
            return AiEvaluator.evaluate(copy.getMap(), myTeam, true);
        }
        
        double value = -1000000;
        List<EvalMove> evalMoves = sortedEvalMoves();
        Collections.reverse(evalMoves);
        for (EvalMove i : evalMoves) {

            copy.doMove(i.getMove());
            
            if (myTeam.contains(copy.nextColorToMove())) {
                value = Math.max(value, maxValue(alpha, beta, depth - 1));
            } else {
                value = Math.max(value, minValue(alpha, beta, depth - 1));
            }
            
            if (beta <= value) {
                copy.undoLastMove();
                return value;
            }
            
            alpha = Math.max(alpha, value);
            
            copy.undoLastMove();
        
        }
        
        return value;
    
    }
    
    public double minValue(double alpha, double beta, int depth) {
        
        if (depth == 0) {
            return AiEvaluator.evaluate(copy.getMap(), myTeam, true);
        }
        double value = 1000000;
        
        List<EvalMove> evalMoves = sortedEvalMoves();
        
        for (EvalMove i : evalMoves) {
            
            copy.doMove(i.getMove());
            
            if (myTeam.contains(copy.nextColorToMove())) {
                value = Math.min(value, maxValue(alpha, beta, depth - 1));
            } else {
                value = Math.min(value, minValue(alpha, beta, depth - 1));
            }
            
            if (alpha >= value) {
                copy.undoLastMove();
                return value;
            }
            
            beta = Math.min(beta, value);
            
            copy.undoLastMove();
        
        }
        return value;
    }

    @Override
    public void calculateMove() {
        moveIsReady = false;
        nextMove = alphaBeta();
        moveIsReady = true;

    }

    @Override
    public ArmyColor getColor() {
        return color;
    }

    @Override
    public boolean moveIsReady() {
        return moveIsReady;
    }

    @Override
    public Move getNextMove() {
        return nextMove;
    }
    
    public List<EvalMove> sortedEvalMoves() {
        List<Move> moves = copy.allPossibleNextMoves();
        List<EvalMove> evalMoves = new ArrayList<>();
        for (Move i : moves) {
            copy.doMove(i);
            double eval = AiEvaluator.evaluate(copy.getMap(), myTeam, true);
            evalMoves.add(new EvalMove(eval, i));
            copy.undoLastMove();
        }
        
        Collections.sort(evalMoves);
        
        return evalMoves;
    
    } 

}
