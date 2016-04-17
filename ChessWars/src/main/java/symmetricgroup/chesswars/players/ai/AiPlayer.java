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
import symmetricgroup.chesswars.battle.BattleMoveCalculator;
import symmetricgroup.chesswars.battle.BattleWinnerChecker;
import symmetricgroup.chesswars.battle.Move;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;

/**
 * AiPlayer on tekoälyn toiminnallisuuden sisältävä luokka.
 */
public class AiPlayer implements Player {
    private BattleMoveCalculator moveCalculator;
    private int searchDepth;
    private ArmyColor color;
    private Set<ArmyColor> myTeam; //Koodia voi muokata siten, että myTeam on tarpeeton
    private Battle battle;
    private Battle copy;
    private boolean moveIsReady;
    private Move nextMove;
    private static final double LARGE = 9000000;

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
        
        moveCalculator = new BattleMoveCalculator(copy);
        
        double value = -LARGE * 2;
        Move best = null;
        
        List<EvalMove> evalMoves = sortedEvalMoves();
        Collections.reverse(evalMoves);
        for (EvalMove i : evalMoves) {
            
            copy.doMove(i.getMove());
            
            if (BattleWinnerChecker.myTeamHasWon(battle, color)) {
                copy.undoLastMove();
                return i.getMove();
            }

            double newValue = calculateValue(-LARGE - searchDepth, LARGE + searchDepth, searchDepth - 1);
            if (newValue > value) {
                value = newValue;
                best = i.getMove();

            }
            
            copy.undoLastMove();
        
        }
        System.out.println(value);
        //System.out.println(best);
        return best;
    }
    
    public double maxValue(double alpha, double beta, int depth) {

        if (depth == 0) {
            return AiEvaluator.evaluate(copy.getMap(), myTeam, true);
        }
        
        double value = -LARGE * 2;
        
        List<EvalMove> evalMoves = sortedEvalMoves();
        Collections.reverse(evalMoves);
        for (EvalMove i : evalMoves) {

            copy.doMove(i.getMove());
            
            double newValue;
            
            if (BattleWinnerChecker.myTeamHasWon(battle, color)) {
                newValue = LARGE + depth;
            } else {
                newValue = calculateValue(alpha, beta, depth);
            }

            value = Math.max(value, newValue);
            
            if (value > 10000) {
                //System.out.println("Tässä value2 :" + value);
            }
            
            copy.undoLastMove();
            
            if (beta <= value) {
                return value;
            }
            
            alpha = Math.max(alpha, value);
            
        
        }
        
        return value;
    }
    
    public double minValue(double alpha, double beta, int depth) {

        if (depth == 0) {
            return AiEvaluator.evaluate(copy.getMap(), myTeam, true);
        }
        double value = LARGE * 2;
        
        List<EvalMove> evalMoves = sortedEvalMoves();
        
        for (EvalMove i : evalMoves) {
            
            copy.doMove(i.getMove());
            
            double newValue;
            
            if (BattleWinnerChecker.myTeamHasLost(battle, color)) {
                newValue = - LARGE - depth;
            } else {
                newValue = calculateValue(alpha, beta, depth);
            }
            
            value = Math.min(value, newValue);
            
            copy.undoLastMove();
            
            if (alpha >= value) {
                return value;
            }
            
            beta = Math.min(beta, value);
            

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
        List<Move> moves = moveCalculator.allPossibleNextMoves();
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
    
    private double calculateValue(double alpha, double beta, int depth) {
        if (myTeam.contains(copy.nextColorToMove())) {
            return maxValue(alpha, beta, depth - 1);
        }  
        return minValue(alpha, beta, depth - 1);  
    }

    public Set<ArmyColor> getMyTeam() {
        return myTeam;
    }
    
    
}
