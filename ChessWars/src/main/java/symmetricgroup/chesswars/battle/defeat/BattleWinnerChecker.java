/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.battle.defeat;

import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.pieces.Piece;
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
    
    /**
     * Metodi palauttaa totuusarvon, joka kertoo onko värillä kuningas kartalla.
     * @param map tutkittava kartta
     * @param color tutkittava väri
     * @return totuusarvo
     */
    public static boolean mapContainsKing(BattleMap map, ArmyColor color) {
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                
                if (kingFoundAt(map, i, j, color)) {
                    return true;
                }
                
            }
        }
    
        return false;
    }
    
    private static boolean kingFoundAt(BattleMap map, int i, int j, ArmyColor color) {
        Piece piece = map.getPiece(i, j);
        if (piece == null) {
            return false;
        }
        return "King".equals(piece.getName()) && piece.getColor() == color;
    }
            
}
