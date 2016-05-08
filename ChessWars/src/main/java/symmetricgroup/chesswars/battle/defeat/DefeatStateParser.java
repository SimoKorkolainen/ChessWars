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
import symmetricgroup.chesswars.players.ArmyColorParser;
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
        
        appendInfo(sb, defeat);
        
        appendMoves(sb, defeat);
        
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
            
            
            DefeatState defeat = createDefeat(conf, players);
            
            parseMoves(conf, 3, defeat);
            
            return defeat;
            
        } catch (Exception e) {
        
        }
        
        
        

        return null;
    }
    
    private static void appendMoves(StringBuilder sb, DefeatState defeat) {
        sb.append(defeat.getDefeatedRemoveMoves().size());
        
        sb.append(" ");
        
        for (Move i : defeat.getDefeatedRemoveMoves()) {
            
            sb.append(i.toString());
            
            sb.append(" ");
        }
    }
    
    private static void appendInfo(StringBuilder sb, DefeatState defeat) {
                
        sb.append(defeat.getPlayer().getColor());
        
        sb.append(" ");
        
        sb.append(defeat.getPlayerPos());
        
        sb.append(" ");
        
        sb.append(defeat.getTurn());
        
        sb.append(" ");
            
    }
    
    
    private static void parseMoves(String[] conf, int ind, DefeatState defeat) {
        int defeatedN = Integer.parseInt(conf[ind]);

        List<Move> moves = new ArrayList<>();

        for (int i = 0; i < defeatedN; i++) {

            parseMove(moves, ind + 1 + i * 6, conf);
            
        }

        defeat.setRemoveMoves(moves);
    }
    
    private static void parseMove(List<Move> moves, int ind, String[] conf) {
        String moveConf = "";

        for (int j = 0; j < 6; j++) {
            moveConf += conf[ind + j] + " ";
        }
        moves.add(Move.stringToMove(moveConf));
    }
    
    private static Player findPlayer(List<Player> list, ArmyColor color) {


        for (Player i : list) {
            if (i.getColor() == color) {
                return i;
            }

        }
        
        return null;
    }
    
    private static DefeatState createDefeat(String[] conf, List<Player> players) {
        ArmyColor color = new ArmyColorParser().stringToArmyColor(conf[0]);

        int turn = Integer.parseInt(conf[1]);

        int playerPos = Integer.parseInt(conf[2]);


        Player player = findPlayer(players, color);

        if (player == null) {
            return null;
        }

        return new DefeatState(player, playerPos, turn);
    }
}
