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
import symmetricgroup.chesswars.battle.move.BattleMoveCalculator;
import symmetricgroup.chesswars.battle.defeat.BattleWinnerChecker;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;
import symmetricgroup.chesswars.ui.game.BattleMoveThread;

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

    private static final double LARGE = 9000000;
    
    /**
     * Konstruktori luo tekoälypelaajan.
     * @param searchDepth haun syvyys
     * @param color tekoälyn väri
     * @param battle taistelu, jossa tekoäly taistelee
     */
    public AiPlayer(int searchDepth, ArmyColor color, Battle battle) {
        this.searchDepth = searchDepth;
        this.color = color;
        this.myTeam = new HashSet<>();
        this.myTeam.add(color);
        this.battle = battle;
        System.out.println("Ai " + searchDepth);  
    }
    
    /**
     * Metodi alustaa tekoälyn joukkueen.
     */
    public void setUpTeam() {
        for (Player i : battle.getPlayers()) {
            if (battle.getTeam(color) == battle.getTeam(i.getColor())) {
                myTeam.add(i.getColor());
            }
        }
    }
    /**
     * Metodi laskee tekoälyn seuraavan siirron
     * Tekoäly pelaa aivan kuin kaikki eri tiimeissä olevat vastustajat
     * olisivat liittoutuneet keskenään ja omien tiimiläisten siirrot olisivat
     * tekoälyn itse päätettävissä.
     * @return paras siirto
     */
    public Move alphaBeta() {
        copy = BattleCopier.copy(battle);
        
        moveCalculator = new BattleMoveCalculator(copy);
        
        double value = -LARGE * 2;
        Move best = null;
        
        List<EvalMove> evalMoves = EvalMove.calculateSortedEvalMoves(copy, myTeam);
        Collections.reverse(evalMoves);
        for (EvalMove i : evalMoves) {
            
            copy.doMove(i.getMove());
            
            if (BattleWinnerChecker.myTeamHasWon(battle, color)) {
                copy.undoLastMove();
                return i.getMove();
            }

            double newValue = calculateValue(-LARGE - searchDepth, LARGE + searchDepth, searchDepth);
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
    
    private double maxValue(double alpha, double beta, int depth) {

        if (depth == 0) {
            return AiEvaluator.evaluate(copy.getMap(), myTeam, true);
        }
        
        double value = -LARGE * 2;
        
        List<EvalMove> evalMoves = EvalMove.calculateSortedEvalMoves(copy, myTeam);
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
            

            copy.undoLastMove();
            
            if (beta <= value) {
                return value;
            }
            
            alpha = Math.max(alpha, value);
        }

        return value;
    }
    
    private double minValue(double alpha, double beta, int depth) {

        if (depth == 0) {
            return AiEvaluator.evaluate(copy.getMap(), myTeam, true);
        }
        double value = LARGE * 2;
        
        List<EvalMove> evalMoves = EvalMove.calculateSortedEvalMoves(copy, myTeam);
        
        for (EvalMove i : evalMoves) {
            
            copy.doMove(i.getMove());
            
            double newValue;
            
            if (BattleWinnerChecker.myTeamHasLost(battle, color)) {
                newValue = -LARGE - depth;
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
    public ArmyColor getColor() {
        return color;
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
    /**
     * Metodi laskee tekoälyn seuraavan siirron ja toteuttaa sen.
     * @param thread metodia kutsunut BattleMoveThread-olio
     */
    @Override
    public void calculateAndExecuteMove(BattleMoveThread thread) { 
        Move next = alphaBeta();
        thread.executeMove(next);  
    }
}
