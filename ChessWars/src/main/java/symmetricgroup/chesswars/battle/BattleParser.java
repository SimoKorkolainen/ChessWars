package symmetricgroup.chesswars.battle;

import java.util.ArrayList;
import java.util.List;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.map.MapParser;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.ArmyColorParser;
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
        
        appendMap(sb, battle);

        appendPlayers(sb, battle);
        
        appendMoves(sb, battle);
        
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
            
            Battle battle = new Battle();

            loadBattle(battle, control, battleConf);

            return battle;
            
        } catch (Exception e) { }
        
        return null;
    }
    
    
    private static void appendPlayers(StringBuilder sb, Battle battle) {
        sb.append(battle.getPlayers().size());
        
        sb.append(" ");
        
        for (Player i : battle.getPlayers()) {
        
            appendPlayer(i, battle, sb); 
        }
    }
    
    private static void appendPlayer(Player i, Battle battle, StringBuilder sb) {
        
        sb.append(i.getColor().toString());
        sb.append(" ");
        sb.append(isAi(i));
        sb.append(" ");
        sb.append(battle.getTeam(i.getColor()));
        sb.append(" ");
            
    }
    
    private static void appendMoves(StringBuilder sb, Battle battle) {
        sb.append(battle.getMoves().size());
        sb.append(" ");
        
        for (Move i : battle.getMoves()) {
        
            sb.append(i);
            sb.append(" ");
        }
    }
    
    private static void appendMap(StringBuilder sb, Battle battle) {
        sb.append(battle.getMap().getMapName());
        
        sb.append(" NAME_END ");
        
        sb.append(MapParser.mapToString(battle.getMap()));

        sb.append("MAP_END ");
    }
    
    private static String[] loadMapAndReturnRest(Battle battle, String conf) {
        String batConf[] = conf.split("NAME_END ");
        String name = batConf[0];

        batConf = batConf[1].split("MAP_END ");
        BattleMap map = MapParser.stringToMap(batConf[0]);

        battle.setMap(map);

        battle.getMap().setMapName(name);

        return batConf;
    } 
    
    private static int loadPlayers(Battle battle, UserControl control, String[] conf, int ind) {
        
        int playerN = Integer.parseInt(conf[ind]);
            
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < playerN; i++) {

            Player player = loadPlayer(battle, control, conf, ind + 1 + i * 3);
            
            players.add(player);
        }
        
        battle.setPlayers(players);
        
        return playerN;
    }
    
    private static Player loadPlayer(Battle battle, UserControl control, String[] conf, int ind) {
        ArmyColor color = new ArmyColorParser().stringToArmyColor(conf[ind]);
        boolean ai = Boolean.valueOf(conf[ind + 1]);

        int team = Integer.parseInt(conf[ind + 2]);

        battle.setTeam(color, team);

        if (ai) {
            return new AiPlayer(4, color, battle, true);
        }
        
        return new UserPlayer(color, control);
    }
    
    private static int loadMoves(Battle battle, String[] conf, int ind) {
        int moveN = Integer.parseInt(conf[ind]);

        List<Move> moves = new ArrayList<>();

        for (int i = 0; i < moveN; i++) {

            moves.add(loadMove(ind + 6 * i + 1, conf));
        }

        battle.setMoves(moves);
        
        return moveN;
    }
    
    private static Move loadMove(int ind, String[] conf) {
        String moveConf = "";
        for (int j = 0; j < 6; j++) {
            moveConf += conf[ind + j] + " ";
        }
        return Move.stringToMove(moveConf);
    }
    
    private static void loadBattle(Battle battle, UserControl control, String battleConf) {
            
        String batConf[] = loadMapAndReturnRest(battle, battleConf);

        String conf[] = batConf[1].split(" ");

        int playerN = loadPlayers(battle, control, conf, 0);

        int ind = 1 + playerN * 3;

        int moveN = loadMoves(battle, conf, ind);

        ind += 6 * moveN + 1;

        int turn = Integer.parseInt(conf[ind]);

        battle.setTurn(turn);
    }
}
