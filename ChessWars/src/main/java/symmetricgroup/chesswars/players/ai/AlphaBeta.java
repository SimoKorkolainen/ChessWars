/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.players.ai;

import java.util.Collections;
import java.util.List;
import symmetricgroup.chesswars.battle.defeat.BattleWinnerChecker;
import symmetricgroup.chesswars.battle.move.Move;

/**
 *
 * AlphaBeta on tekoälyä varten parhaan siirron laskemiseen tehty luokka.
 */
public class AlphaBeta {
    private AiPlayer player;
    private static final double LARGE = 9000000;
    /**
     * AlphaBeta olion konstruktori.
     * @param player käyttettävä pelaaja
     */
    public AlphaBeta(AiPlayer player) {
        this.player = player;
    }
    
    /**
     * Metodi laskee tekoälyn seuraavan siirron
     * Tekoäly pelaa aivan kuin kaikki eri tiimeissä olevat vastustajat
     * olisivat liittoutuneet keskenään ja omien tiimiläisten siirrot olisivat
     * tekoälyn itse päätettävissä.
     * @return paras siirto
     */
    public Move alphaBeta() {
        
        double value = -LARGE * 2;
        
        List<EvalMove> evalMoves = EvalMove.calculateSortedEvalMoves(player.getCopy(), player.getMyTeam());
        Collections.reverse(evalMoves);

        return maxFindBestMove(evalMoves, value);
    }
    
    private Move maxFindBestMove(List<EvalMove> evalMoves, double value) {
        EvalMove best = new EvalMove(value, null);
        for (EvalMove i : evalMoves) {

            best = updateMaxBest(best, i.getMove());
            
            if (best.getEval() >= 3 * LARGE) {
                break;
            }
        }
        return best.getMove();
    }
    
    private EvalMove updateMaxBest(EvalMove best, Move move) {
        best = checkVictory(best, move);

        if (best.getEval() >= 3 * LARGE) {
            return best;
        }

        return updateBest(best, move);
    }
    
    private EvalMove checkVictory(EvalMove best, Move move) {
        player.getCopy().doMove(move);

        if (BattleWinnerChecker.myTeamHasWon(player.getBattle(), player.getColor())) {
            player.getCopy().undoLastMove();
            return new EvalMove(3 * LARGE, move);
        }
        
        return best;
    }
    
    private EvalMove updateBest(EvalMove best, Move move) {

        double newValue = calculateValue(-LARGE - player.getSearchDepth(), LARGE + player.getSearchDepth(), player.getSearchDepth());
        player.getCopy().undoLastMove();
        
        if (newValue > best.getEval()) {
            return new EvalMove(newValue, move);
        }
        
        return best;
    }
    
    private double maxValue(double alpha, double beta, int depth) {

        if (depth == 0) {
            return AiEvaluator.evaluate(player.getCopy().getMap(), player.getMyTeam(), player.isRandom());
        }
        
        double value = -LARGE * 2;
        
        List<EvalMove> evalMoves = EvalMove.calculateSortedEvalMoves(player.getCopy(), player.getMyTeam());
        Collections.reverse(evalMoves);


        return maxCheckMoves(evalMoves, value, alpha, beta, depth);
    }
    
    private double maxCheckMoves(List<EvalMove> evalMoves, double value, double alpha, double beta, int depth) {
        for (EvalMove i : evalMoves) {

            value = updateValue(i.getMove(), value, depth, alpha, beta, false);

            if (beta <= value) {
                return value;
            }
            
            alpha = Math.max(alpha, value);
        }
        return value;
    }
    
    private double minCheckMoves(List<EvalMove> evalMoves, double value, double alpha, double beta, int depth) {
        for (EvalMove i : evalMoves) {

            value = updateValue(i.getMove(), value, depth, alpha, beta, true);
            
            if (alpha >= value) {
                return value;
            }
            
            beta = Math.min(beta, value);

        }
        return value;
    }
    
    private double minValue(double alpha, double beta, int depth) {

        if (depth == 0) {
            return AiEvaluator.evaluate(player.getCopy().getMap(), player.getMyTeam(), player.isRandom());
        }
        
        double value = LARGE * 2;
        
        List<EvalMove> evalMoves = EvalMove.calculateSortedEvalMoves(player.getCopy(), player.getMyTeam());
        
        return minCheckMoves(evalMoves, value, alpha, beta, depth);
    }
    
    private double updateValue(Move move, double value, int depth, double alpha, double beta, boolean min) {
        player.getCopy().doMove(move);
        
        if (min) {
            value = minUpdateValue(value, depth, alpha, beta);
        } else {
            value = maxUpdateValue(value, depth, alpha, beta);
        }
        
        player.getCopy().undoLastMove();
        
        return value;
    }
    
    private double minUpdateValue(double value, int depth, double alpha, double beta) {
        double newValue;

        if (BattleWinnerChecker.myTeamHasLost(player.getBattle(), player.getColor())) {
            newValue = -LARGE - depth;
        } else {
            newValue = calculateValue(alpha, beta, depth);
        }

        return Math.min(value, newValue);
    }
    
    private double maxUpdateValue(double value, int depth, double alpha, double beta) {
        double newValue;

        if (BattleWinnerChecker.myTeamHasWon(player.getBattle(), player.getColor())) {
            newValue = LARGE + depth;
        } else {
            newValue = calculateValue(alpha, beta, depth);
        }

        return Math.max(value, newValue);
    }

    private double calculateValue(double alpha, double beta, int depth) {
        if (player.getMyTeam().contains(player.getCopy().nextColorToMove())) {
            return maxValue(alpha, beta, depth - 1);
        } 
        return minValue(alpha, beta, depth - 1);  
    }
    
    
}
