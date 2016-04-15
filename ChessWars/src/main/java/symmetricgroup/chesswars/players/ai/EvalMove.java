/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.players.ai;

import symmetricgroup.chesswars.battle.Move;

/**
 * EvalMove on pari, joka sisältää siirron ja siirtoa seuraavassa kartan tilassa lasketun heuristiikkafunktion arvon.
 * EvalMove toteuttaa Comparable-rajapinnan, jotta siirrot olisi mahdollista järjestää paremmuusjärjestykseen.
 */
public class EvalMove implements Comparable<EvalMove> {
    private double eval;
    private Move move;

    public EvalMove(double eval, Move move) {
        this.eval = eval;
        this.move = move;
    }

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
    
    
    
}
