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
    
    
    /**
     * Metodi kertoo onko värin joukkue voittanut taistelun
     * @param battle taistelu, jonka tilannetta halutaan tutkia
     * @param color väri, jonka voitosta halutaan tietoa
     * @return palauttaa true jos ja vain jos värin joukkue on voittanut
     */
    public static boolean myTeamHasWon(Battle battle, ArmyColor color) {
        
        for (Player i : battle.getPlayers()) {
            if (battle.getTeam(color) != battle.getTeam(i.getColor())) {
                return false;
            }
        }
        
        return true;
    }
    
     /**
     * Metodi kertoo onko värin joukkue hävinnyt taistelun
     * @param battle taistelu, jonka tilannetta halutaan tutkia
     * @param color väri, jonka joukkueen häviöstä halutaan tietoa
     * @return palauttaa true jos ja vain jos värin joukkue on hävinnyt
     */
    public static boolean myTeamHasLost(Battle battle, ArmyColor color) {

        for (Player i : battle.getPlayers()) {
            if (battle.getTeam(color) == battle.getTeam(i.getColor())) {
                return false;
            }
        }
        
        return true;
    }
}
