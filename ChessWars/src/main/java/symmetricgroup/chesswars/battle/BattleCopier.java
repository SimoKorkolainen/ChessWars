/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.battle;

import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.map.MapCopier;

/**
 *
 * @author Simo
 */
public class BattleCopier {
    public static Battle copy(Battle battle) {
        // Metodi copy ei kopioi Battle-oliota täydellisesti, sillä kopiolla ja alkuperäisellä on yhteisiä olioita.
        Battle copy = new Battle(MapCopier.copy(battle.getMap()));
        copy.setTeam(battle.getTeam());
        copy.setTurn(battle.getTurn());
        copy.setMoves(battle.getMoves());
        copy.setPlayers(battle.getPlayers());
        return copy;
    }
}
