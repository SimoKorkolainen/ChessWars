/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.battle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.map.MapParser;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;
import symmetricgroup.chesswars.players.ai.AiPlayer;
import symmetricgroup.chesswars.players.ui.UserControl;
import symmetricgroup.chesswars.players.ui.UserPlayer;


/**
 * BattleParser on tarkoitettu taistelun muuttamiseen tekstiksi ja toisinpäin.
 * @author Simo
 */
public class BattleParser {
      /**
     * Metodi muuttaa taistelun String-muotoon.
     * @param battle taistelu, joka halutaan muuttaa tekstiksi
     * @return palauttaa taistelun tiedot sisältävän tekstin
     */
    public static String battleToString(Battle battle) {
    
        StringBuilder sb = new StringBuilder();
        
        sb.append(battle.getMap().getMapName());
        
        sb.append(" NAME_END ");
        
        
        sb.append(MapParser.mapToString(battle.getMap()));

        sb.append("MAP_END ");
        
        sb.append(battle.getPlayers().size());
        
        sb.append(" ");
        
        for (Player i : battle.getPlayers()) {
        
            sb.append(i.getColor().toString());
            sb.append(" ");
            sb.append(isAi(i));
            sb.append(" ");
            sb.append(battle.getTeam(i.getColor()));
            sb.append(" ");
            
        }
        
        sb.append(battle.getMoves().size());
        
        sb.append(" ");
        
        for (Move i : battle.getMoves()) {
        
            sb.append(i);
            
            sb.append(" ");
        }
        
        
        sb.append(battle.getTurn());

       
        return sb.toString();
    
    }
    
  
    
    private static boolean isAi(Player player) {
        
        return player.getClass() == AiPlayer.class;
    
    }
    /**
     * Metodi luo taistelun tekstissä olevien tietojen perusteella.
     * 
     * @param battleConf taistelun tiedot sisältävä teksti
     * @param control käytttäjien luomiseen ja ohjaamiseen tarkoitettu olio
     * @return palautta tekstin perusteella valmistetun taistelun
     */
    public static Battle stringToBattle(String battleConf, UserControl control) {
        
        try {
            
            
            
            String batConf[] = battleConf.split("NAME_END ");
            
            String name = batConf[0];
            
            batConf = batConf[1].split("MAP_END ");
            
            BattleMap map = MapParser.stringToMap(batConf[0]);
            
            Battle battle = new Battle(map);
            System.out.println("Creating new battle: " + name);
            battle.getMap().setMapName(name);
            
            String conf[] = batConf[1].split(" ");

            int playerN = Integer.parseInt(conf[0]);
            
            List<Player> players = new ArrayList<>();
            
            for (int i = 0; i < playerN; i++) {
                
                ArmyColor color = ArmyColor.stringToArmyColor(conf[1 + i * 3]);
                boolean ai = Boolean.valueOf(conf[2 + i * 3]);
                System.out.println(ai + " " + conf[2 + i * 3]);
                int team = Integer.parseInt(conf[3 + i * 3]);
                
                battle.setTeam(color, team);
                Player player;

                if (ai) {
                    player = new AiPlayer(4, color, battle);
                } else {
                    player = new UserPlayer(color, control);
                }
                
                players.add(player);
            }
            int ind = 1 + playerN * 3;
            int moveN = Integer.parseInt(conf[ind]);
            
            List<Move> moves = new ArrayList<>();
            
            for (int i = 0; i < moveN; i++) {
                String moveConf = "";
                for (int j = 0; j < 6; j++) {
                    moveConf += conf[ind + 6 * i + j + 1] + " ";
                }
                moves.add(Move.stringToMove(moveConf));
            
            }
            ind += 6 * moveN + 1;
            
            int turn = Integer.parseInt(conf[ind]);
        
            battle.setPlayers(players);
            battle.setMoves(moves);
            battle.setTurn(turn);
            
            System.out.println("Loaded battle has " + battle.getPlayers().size() + " players");
            
            return battle;
            
        } catch (Exception e) {
        
            System.out.println("Unable to parse battle: " + e.getMessage());
        
        }
        
        return null;
    }
    
}
