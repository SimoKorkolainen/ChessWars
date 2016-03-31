/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.players.ai;

import symmetricgroup.chesswars.battle.Move;

/**
 *
 * @author Simo
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
