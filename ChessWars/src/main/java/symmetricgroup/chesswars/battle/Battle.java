/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.battle;

import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.pieces.Piece;

/**
* Battle-luokka mallintaa taistelun kulkua kartalla.
*/
public class Battle {
    private BattleMap map;
    private Map<ArmyColor, Integer> team;

    private List<Player> players;
    private int turn;
    private List<Move> moves;
    private List<DefeatState> defeatStates;

    public Battle(BattleMap map) {
        this.map = map;
        this.team = new HashMap<>();

        this.players = new ArrayList<>();
        this.moves = new ArrayList<>();
        this.defeatStates = new ArrayList<>();
        this.turn = 0;
       
        setDefaultTeams();
        
    }
    
    public void start() {
        Player next = nextPlayerToMove();
        next.calculateMove();
        BattleThread thread = new BattleThread(this);
        thread.start();
    }
    
    
    public void setTeam(ArmyColor color, int teamNumber) {
        team.put(color, teamNumber);
    }
    /**
     * Metodi asettaa oletusarvoiset joukkueet.
     * Jokainen pelaaja on oletusarvoisesti eri joukkueessa.
     *
     */
    public void setDefaultTeams() {
        setTeam(ArmyColor.WHITE, 1);
        setTeam(ArmyColor.BLACK, 2);
        setTeam(ArmyColor.BLUE, 3); 
        setTeam(ArmyColor.RED, 4); 
        setTeam(ArmyColor.GREEN, 5);
        setTeam(ArmyColor.YELLOW, 6);
    }
    
    public void addPlayer(Player player) {
        
        players.add(player);
    
    }
    /**
     * Metodi toteuttaa siirron taistelussa.
     *
     * @param   move  Siirto, joka halutaan tehdä
     * 
     * @see symmetricgroup.chesswars.battle.Move
     */
    public void doMove(Move move) {
        turn = (turn + 1) % players.size();
        map.doMove(move);
        moves.add(move);
        
        Piece eaten = move.getEaten();
        if (eaten != null && "King".equals(eaten.getName())) {
        
            handleDefeat(eaten.getColor());
        
        }
    }

    private void undoMove(Move move) {
        Piece eaten = move.getEaten();
        if (eaten != null && "King".equals(eaten.getName())) {
        
            undoDefeat(defeatStates.remove(defeatStates.size() - 1));
        
        }
        
        
        turn = (turn - 1 + players.size()) % players.size();
        map.undoMove(move);
       
    }
    
    /**
     * Metodi kumoaa edellisen siirron taistelussa.
     *
     * @see symmetricgroup.chesswars.battle.Move
     */
    public void undoLastMove() {
        undoMove(moves.remove(moves.size() - 1));
    }
    /**
     * Metodi palauttaa seuraavaksi vuorossa olevan pelaajan värin.
     *
     * @return Seuraavan pelaajan väri 
     */
    public ArmyColor nextColorToMove() {
        return players.get(turn).getColor();
    }
    /**
     * Metodi palauttaa seuraavaksi vuorossa olevan pelaajan.
     *
     * @return Seuraava pelaaja 
     */
    public Player nextPlayerToMove() {
        return players.get(turn);
    }
    public int getTeam(ArmyColor color) {
        return team.get(color);
    }

    public BattleMap getMap() {
        return map;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Move getLastMove() {
        return moves.get(moves.size() - 1);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Map<ArmyColor, Integer> getTeam() {
        return team;
    }

    public void setTeam(Map<ArmyColor, Integer> team) {
        this.team = team;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }


    public void handleDefeat(ArmyColor color) {
        Player loser = null;
        int playerPos = 0;
        
        for (Player i : players) {
            
            if (i.getColor() == color) {
                loser = i;
                break;
            }
            
            playerPos++;
        }
        if (loser != null) {
            if (playerPos < turn) {
                turn--;
            }
            
            players.remove(playerPos);
            
            turn %= players.size();
            
            DefeatState defeat = new DefeatState(loser, playerPos);

            defeat.removeDefeated(map);
            
            defeatStates.add(defeat);
        }
    }
    
    public void undoDefeat(DefeatState defeat) {
        
        defeat.putDefeatedBack(map);
        
        players.add(defeat.getPlayerPos(), defeat.getPlayer());
        
        if (defeat.getPlayerPos() <= turn) {
            turn++;
        }
    }

    public List<DefeatState> getDefeatStates() {
        return defeatStates;
    }
    
}
