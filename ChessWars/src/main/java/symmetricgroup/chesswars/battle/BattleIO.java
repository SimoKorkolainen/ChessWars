/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.battle;

import symmetricgroup.chesswars.players.Player;
import symmetricgroup.chesswars.players.ai.*;
/**
 *
 * @author simokork
 */
public class BattleIO {
    public String battleToString(Battle battle) {
    
        StringBuilder sb = new StringBuilder();
        
        sb.append(battle.getMap().toString());

        sb.append(battle.getPlayers().size());
        
        sb.append(" ");
        
        sb.append(" ");
        
        sb.append(battle.getTurn());
        
        for (Player i : battle.getPlayers()) {
        
            sb.append(i.getColor().toString());
            sb.append(" ");
            sb.append(isAi(i));
            sb.append(" ");
            sb.append(battle.getTeam(i.getColor()));
            sb.append(" ");
            
        }
        
        
           
        
        return sb.toString();
    
    }
    
    
    private boolean isAi(Player player) {
        
        return player.getClass() == AiPlayer.class;
    
    }
}
