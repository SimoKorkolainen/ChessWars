/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.players.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.battle.move.BattleMoveCalculator;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.players.ArmyColor;

/**
 * EvalMove on pari, joka sisältää siirron ja siirtoa seuraavassa kartan tilassa lasketun heuristiikkafunktion arvon.
 * EvalMove toteuttaa Comparable-rajapinnan, jotta siirrot olisi mahdollista järjestää paremmuusjärjestykseen.
 */
public class EvalMove implements Comparable<EvalMove> {
    private double eval;
    private Move move;
    
    /**
     * Konstruktori luo evaluaation-siirto parin.
     * @param eval evaluaatio
     * @param move siirto
     */
    public EvalMove(double eval, Move move) {
        this.eval = eval;
        this.move = move;
    }
    /**
     * Metodi vertaa siirtoja keskenään
     * @param other verrattava siirto
     * @return palauttaa siirtojen järjestyksen indikoivan kokonaisluvun
     */
    @Override
    public int compareTo(EvalMove other) {
        return (int) Math.signum(eval - other.getEval());
    }

    public double getEval() {
        return eval;
    }

    public Move getMove() {
        return move;
    }
    /**
     * Metodi laskee taistelun kaikkien mahdollisten siirtojen evaluaatiot
     * ja järjestää siirrot evaluaatioiden perusteella.
     * @param battle taistelu, josta siirrot lasketaan
     * @param myTeam pelaajan joukkue
     * @return siirrot järjestettynä
     */
    public static List<EvalMove> calculateSortedEvalMoves(Battle battle, Set<ArmyColor> myTeam) {
        BattleMoveCalculator calculator = new BattleMoveCalculator(battle);
        
        List<Move> moves = calculator.allPossibleNextMoves();
        List<EvalMove> evalMoves = new ArrayList<>();
        for (Move i : moves) {
            addEvaluatedMove(battle, myTeam, evalMoves, i);
        }
        
        Collections.sort(evalMoves);
        
        return evalMoves;
    
    } 
    
    private static void addEvaluatedMove(Battle battle, Set<ArmyColor> myTeam, List<EvalMove> evalMoves, Move i) {
        battle.doMove(i);
        double eval = AiEvaluator.evaluate(battle.getMap(), myTeam, true);
        evalMoves.add(new EvalMove(eval, i));
        battle.undoLastMove();
    }
    
}
