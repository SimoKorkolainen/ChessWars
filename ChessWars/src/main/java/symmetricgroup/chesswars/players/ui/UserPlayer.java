/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.players.ui;

import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;
import symmetricgroup.chesswars.ui.game.BattleMoveThread;

/**
 * UserPlayer on käyttäjän tiedot sisältävä luokka.
 * UserPlayer-oliolta voi kysyä käyttäjän seuraavaa siirtoa.
 */
public class UserPlayer implements Player {

    private UserControl control;
    private ArmyColor color;
    private BattleMoveThread moveThread;
    
    public UserPlayer(ArmyColor color, UserControl control) {
        this.color = color;
        this.control = control;
    }


    

    public ArmyColor getColor() {
        return color;
    }




    @Override
    public void calculateAndExecuteMove(BattleMoveThread thread) {
        control.setPlayer(this);
        moveThread = thread;
    }
    
    public void executeMove(Move move) {
        moveThread.executeMove(move);
    }

}
