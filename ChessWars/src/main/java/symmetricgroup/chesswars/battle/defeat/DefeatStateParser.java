/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.battle.defeat;

import java.util.ArrayList;
import java.util.List;
import symmetricgroup.chesswars.battle.DefeatState;
import symmetricgroup.chesswars.battle.Move;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;

/**
 *
 * @author Simo
 */
public class DefeatStateParser {
    public String defeatStateToString(DefeatState defeat) {
        
        
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
    
    public DefeatState stringToDefeatState(String defeatConf, List<Player> players) {
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
                    moveConf += conf[3 + i * 6] + " ";
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
