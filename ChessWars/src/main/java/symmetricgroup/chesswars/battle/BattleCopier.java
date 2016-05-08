/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.battle;

import java.util.ArrayList;
import java.util.List;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.map.MapCopier;
import symmetricgroup.chesswars.players.Player;

/**
 * BattleCopier on tekoälyn käyttöön tarkoitettu luokka, jonka avulla on mahdollista kopioida Battle-olioita lähes kokonaan.
 * Kopiolla ja alkuperäisellä Battle-oliolla voi olla samoja olioita attribuutteina.
 */
public class BattleCopier {
    
    /**
     * Metodi kopio osittain Battle-olion. Metodi copy ei kopioi Battle-oliota täydellisesti,
     * sillä kopiolla ja alkuperäisellä on yhteisiä olioita.
     * 
     * @param battle kopiotava taistelu
     * @return kopioitu taistelu
     */
    public static Battle copy(Battle battle) {
        
        Battle copy = new Battle(MapCopier.copy(battle.getMap()));
        copy.setTeam(battle.getTeam());
        copy.setTurn(battle.getTurn());
        
        copy.setMoves(copyMoves(battle.getMoves()));
        
        copy.setPlayers(copyPlayers(battle.getPlayers(), copy));
        
        return copy;
    }
    
    
    private static List<Move> copyMoves(List<Move> moves) {
        List<Move> copy = new ArrayList<>();
        
        for (Move i : moves) {
        
            copy.add(i.copy());
        
        }
        return copy;
    }
    
    
    private static List<Player> copyPlayers(List<Player> players, Battle reference) {
        List<Player> copy = new ArrayList<>();
        
        for (Player i : players) {
        
            copy.add(i.copy(reference));
        
        }
        return copy;
    }
}
