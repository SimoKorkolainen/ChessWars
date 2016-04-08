/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.players.ui;

import symmetricgroup.chesswars.battle.Move;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;

/**
 *
 * @author Simo
 */
public class UserPlayer implements Player {
    private boolean moveIsReady;
    private Move next;
    private UserControl control;
    private ArmyColor color;

    public UserPlayer(ArmyColor color, UserControl control) {
        this.color = color;
        this.moveIsReady = false;
        this.control = control;
    }


    public void setNextMove(Move next) {
        this.next = next;
        this.moveIsReady = true;
    }
    
    

    public ArmyColor getColor() {
        return color;
    }

    @Override
    public void calculateMove() {
        moveIsReady = false;
        control.setPlayer(this);

    }

    @Override
    public boolean moveIsReady() {
        return moveIsReady;
    }

    @Override
    public Move getNextMove() {
        return next;
    }

}
