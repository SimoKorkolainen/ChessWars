/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.battle.defeat;

import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;

/**
 *
 * @author Simo
 */
public class BattleDefeatHandler {
    
    /**
     * Metodi toteuttaa pelaajan häviön
     * @param battle taistelu, jossa pelaaja häviää
     * @param color pelaajan väri
     * 
     */
    public static void handleDefeat(Battle battle, ArmyColor color) {
        int turn = battle.getTurn();
        Player loser = null;
        int playerPos = 0;
        
        for (Player i : battle.getPlayers()) {
            
            if (i.getColor() == color) {
                loser = i;
                break;
            }
            
            playerPos++;
        }
        System.out.println("playerPos " + playerPos);
        if (loser != null) {
            if (playerPos < battle.getTurn()) {
                battle.setTurn(battle.getTurn() - 1);
            }
            
            battle.getPlayers().remove(playerPos);
            
            battle.setTurn(battle.getTurn() % battle.getPlayers().size());
            
            DefeatState defeat = new DefeatState(loser, playerPos, turn);

            defeat.removeDefeated(battle.getMap());
            
            battle.getDefeatStates().add(defeat);
        }
    }
    /**
     * Metodi kumoaa pelaajan häviön
     * @param battle taistelu, jossa pelaaja häviää
     * @param defeat kumottava häviö
     * 
     */
    public static void undoDefeat(Battle battle, DefeatState defeat) {
        
        defeat.putDefeatedBack(battle.getMap());
        
        battle.getPlayers().add(defeat.getPlayerPos(), defeat.getPlayer());
        
        
        battle.setTurn(defeat.getTurn());
        
    }

}
