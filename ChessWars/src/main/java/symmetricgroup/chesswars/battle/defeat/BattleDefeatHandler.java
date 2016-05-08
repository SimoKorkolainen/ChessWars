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
        DefeatState defeat = createDefeat(battle, color);
        if (defeat == null) {
            return;
        }
        updateTurn(defeat, battle);

        defeat.removeDefeated(battle.getMap());

        battle.getDefeatStates().add(defeat);
        
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
    
    private static DefeatState createDefeat(Battle battle, ArmyColor color) {
        int turn = battle.getTurn();
        
        int playerPos = findPlayerPosition(battle, color);
        
        if (playerPos == -1) {
            return null;
        }
        Player loser = battle.getPlayers().remove(playerPos);
        DefeatState defeat = new DefeatState(loser, playerPos, turn);
        
        return defeat;
    }
    
    private static int findPlayerPosition(Battle battle, ArmyColor color) {
        int playerPos = 0;
        
        for (Player i : battle.getPlayers()) {
            
            if (i.getColor() == color) {
                return playerPos;
            }
            
            playerPos++;
        }
        
        return -1;
    }
    
    private static void updateTurn(DefeatState defeat, Battle battle) {
        int playerPos = defeat.getPlayerPos();
        
        if (playerPos < battle.getTurn()) {
            battle.setTurn(battle.getTurn() - 1);
        }

        battle.setTurn(battle.getTurn() % battle.getPlayers().size());

    }

}
