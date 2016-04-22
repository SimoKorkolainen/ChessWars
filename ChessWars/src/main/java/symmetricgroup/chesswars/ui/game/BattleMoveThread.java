/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.game;

import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.battle.BattleWinnerChecker;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;

/**
 * BattleThread on luokka, joka pyytää pelaajia tekemään heidän seuraavat siirtonsa.
 * Siirto pyydeään erillisessä säikeessä, jottei tekoälyn siirtojen laskeminen hidastaisi käyttöliittymää liikaa.
 */
public class BattleMoveThread extends Thread {
    private Battle battle;
    public BattleMoveThread(Battle battle) {
        this.battle = battle;
        
    }
    

    
    @Override
    public void run() {
 
        Player next = battle.nextPlayerToMove();
        
        next.calculateAndExecuteMove(this);
        
    }
    

    public void askPlayerForNextMove() {

        BattleMoveThread thread = new BattleMoveThread(battle);
        thread.start();

    }

    
    
    public void executeMove(Move move) {
       
        battle.doMove(move);
        
        if (!BattleWinnerChecker.myTeamHasWon(battle, battle.nextColorToMove())) {
            askPlayerForNextMove();
        }
        
    }

   
}
