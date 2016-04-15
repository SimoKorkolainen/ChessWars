/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.battle;

import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.players.Player;

/**
 * BattleThread on luokka, joka pyytää pelaajia tekemään heidän seuraavat siirtonsa.
 * Siirto pyydeään erillisessä säikeessä, jottei tekoälyn siirtojen laskeminen hidastaisi käyttöliittymää liikaa.
 */
public class BattleThread extends Thread {
    private Battle battle;
    public BattleThread(Battle battle) {
        this.battle = battle;
        
    }
    

    
    @Override
    public void run() {

        
        Player next = battle.nextPlayerToMove();
        System.out.println("next move " + next.getColor().toString() + "?");
        while (!next.moveIsReady()) {
            sleep();
        }
        
        
        Move move = next.getNextMove();

        battle.doMove(move);
        
        if (BattleWinnerChecker.myTeamHasWon(battle, battle.nextColorToMove())) {
            System.out.println("Team " + battle.getTeam(battle.nextColorToMove()) + " has won!");
            return;
        }

        askPlayerForNextMove();
        

    }
    

    public void askPlayerForNextMove() {
        Player next = battle.nextPlayerToMove();
        next.calculateMove();
        BattleThread thread = new BattleThread(battle);
        thread.start();

    }
    public void sleep() {
        
        try {
            
            Thread.sleep(200);
            
            
        } catch (InterruptedException e) {
        
        }
        
    }

   
}
