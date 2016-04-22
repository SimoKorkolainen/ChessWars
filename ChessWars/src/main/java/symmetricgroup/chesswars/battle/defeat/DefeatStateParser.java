/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.battle.defeat;

import java.util.ArrayList;
import java.util.List;
import symmetricgroup.chesswars.battle.defeat.DefeatState;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;

/**
 *
 * @author Simo
 */
public class DefeatStateParser {
    
    /**
     * Metodi muuttaa lopputilan tekstimuotoon.
     * @param defeat lopputila, joka halutaan muuttaa tekstimuotoon
     * @return lopputilan tiedot sisältävän tekstin
     */
    public static String defeatStateToString(DefeatState defeat) {
        
        
        StringBuilder sb = new StringBuilder();
                
        sb.append(defeat.getPlayer().getColor());
        
        sb.append(" ");
        
        sb.append(defeat.getPlayerPos());
        
        sb.append(" ");
            
        sb.append(defeat.getDefeatedRemoveMoves().size());
        
        sb.append(" ");
        
        for (Move i : defeat.getDefeatedRemoveMoves()) {
            
            sb.append(i.toString());
            
            sb.append(" ");
        }
        
        return sb.toString();
    }
    
    /**
     * Metodi luo lopputilan tekstissä olevien tietojen perusteella.
     * @param defeatConf lopputilan tiedot sisältävä teksti
     * @param players lista pelaajista, joiden perusteella lopputila halutaan luoda
     * @return palauttaa lopputilan tekstinä
     */
    public static DefeatState stringToDefeatState(String defeatConf, List<Player> players) {
        String conf[] = defeatConf.split(" ");
        try {
            
            
            ArmyColor color = ArmyColor.stringToArmyColor(conf[0]);
            
            int playerPos = Integer.parseInt(conf[1]);
            
            Player player = null;
            
            for (Player i : players) {
                if (i.getColor() == color) {
                    player = i;
                }
            
            }

            if (player == null) {
                return null;
            }
            
            DefeatState defeat = new DefeatState(player, playerPos);
            
            int defeatedN = Integer.parseInt(conf[2]);
            
            List<Move> moves = new ArrayList<>();
            
            
            
            for (int i = 0; i < defeatedN; i++) {
                String moveConf = "";
                
                for (int j = 0; j < 6; j++) {
                    moveConf += conf[3 + i * 6 + j] + " ";
                }
                moves.add(Move.stringToMove(moveConf));
                
            }
            
            defeat.setRemoveMoves(moves);
            
            return defeat;
            
        } catch (Exception e) {
        
        }
        
        
        

        return null;
    }
}
