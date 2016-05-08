/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.battle;

import symmetricgroup.chesswars.players.ArmyColor;

/**
 *
 * @author Simo
 */
public class BattleTeamSetter {
    /**
     * Metodi asettaa oletusarvoiset joukkueet.
     * Jokainen pelaaja on oletusarvoisesti eri joukkueessa.
     *
     * @param battle taistelu, jonka joukkueet halutaan alustaa
     */
    public static void setDefaultTeams(Battle battle) {
        battle.setTeam(ArmyColor.WHITE, 1);
        battle.setTeam(ArmyColor.BLACK, 2);
        battle.setTeam(ArmyColor.BLUE, 3); 
        battle.setTeam(ArmyColor.RED, 4); 
        battle.setTeam(ArmyColor.GREEN, 5);
        battle.setTeam(ArmyColor.YELLOW, 6);
    }
}
