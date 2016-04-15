/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.battle;

import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;

/**
 *
 * @author Simo
 */
public class BattleWinnerChecker {
    public static boolean myTeamHasWon(Battle battle, ArmyColor color) {
        
        for (Player i : battle.getPlayers()) {
            if (battle.getTeam(color) != battle.getTeam(i.getColor())) {
                return false;
            }
        }
        
        return true;
    }
    public static boolean myTeamHasLost(Battle battle, ArmyColor color) {

        for (Player i : battle.getPlayers()) {
            if (battle.getTeam(color) == battle.getTeam(i.getColor())) {
                return false;
            }
        }
        
        return true;
    }
}
