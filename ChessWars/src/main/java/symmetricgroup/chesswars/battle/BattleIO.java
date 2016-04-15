/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.battle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.map.MapParser;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;
import symmetricgroup.chesswars.players.ai.*;
import symmetricgroup.chesswars.players.ui.UserControl;
import symmetricgroup.chesswars.players.ui.UserPlayer;
/**
 * BattleIO on luokka, jonka avulla Battle-olio voidaan tallentaa tiedostoon ja lukea tiedostosta.
 */
public class BattleIO {
    
    public static void saveBattle(String filename, Battle battle) {
        String battleConf = battleToString(battle);

        
        try  {
            
            File file = new File(filename);
            file.getParentFile().mkdirs();
            file.createNewFile();
            
            FileWriter writer = new FileWriter(filename);
            
            writer.append(battleConf);
            writer.close();
            
        } catch (Exception e) {
        
            System.out.println("Unable to save the map " + filename + " :"  + e.getLocalizedMessage());
            
        }
    
    }
    public static Battle readBattle(String filename, UserControl control) {


        
        try  {
            File file = new File(filename);
            
            System.out.println(file.getAbsolutePath());
            
            BufferedReader reader = new BufferedReader(new FileReader(file));

            
            String battleConf = reader.readLine();

            return stringToBattle(battleConf, control);
            
        } catch (Exception e) {
        
            System.out.println("Unable to read the map " + filename + " :" + e.getLocalizedMessage());
            
        }
        return null;
    }
    
    public static String battleToString(Battle battle) {
    
        StringBuilder sb = new StringBuilder();
        
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
    
    public static Battle stringToBattle(String battleConf, UserControl control) {
        
        try {
            
            String batConf[] = battleConf.split("MAP_END ");
            
            BattleMap map = MapParser.stringToMap(batConf[0]);
            
            Battle battle = new Battle(map);
            
            String conf[] = batConf[1].split(" ");

            int playerN = Integer.parseInt(conf[0]);
            
            List<Player> players = new ArrayList<>();
            Map<ArmyColor, Integer> teams = new HashMap<>();
            
            for (int i = 0; i < playerN; i++) {
                
                ArmyColor color = ArmyColor.stringToArmyColor(conf[1 + i * 3]);
                boolean ai = Boolean.valueOf(conf[2 + i * 3]);
                System.out.println(ai + " " + conf[2 + i * 3]);
                int team = Integer.parseInt(conf[3 + i * 3]);
                
                teams.put(color, team);
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
            return battle;
            
        } catch (Exception e) {
        
            System.out.println("Unable to parse battle: " + e.getMessage());
        
        }
        
        return null;
    }
    
}
