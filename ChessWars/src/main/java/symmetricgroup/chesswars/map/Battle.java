/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.map;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import symmetricgroup.chesswars.pieces.Piece;

/**
 *
 * @author Simo
 */
public class Battle {
    private BattleMap map;
    private Map<ArmyColor, Integer> team;

    private List<Player> players;
    private int turn;
    private List<Move> moves;

    public Battle(BattleMap map) {
        this.map = map;
        this.team = new HashMap<>();


        this.players = new ArrayList<>();
        this.moves = new ArrayList<>();
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
    
    public void setDefaultTeams() { //Jokainen pelaaja on eri joukkueessa
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
    

    
    public void doMove(Move move) {
        turn = (turn + 1) % players.size();
        map.doMove(move);
        moves.add(move);
    }
    
    private void undoMove(Move move) {
        turn = (turn - 1 + players.size()) % players.size();
        map.undoMove(move);
       
    }
    public void undoLastMove() {
        undoMove(moves.remove(moves.size() - 1));
    }
   
 
    public List<Move> allPossibleNextMoves() {
        List<Move> all = new ArrayList<>();
        
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                
                addMovesInPosition(all, i, j); 
                
            }
        
        }
    
        return all;
    }
    
    private void addMovesInPosition(List<Move> moves, int i, int j) {
        
        Piece piece = map.getPiece(i, j);

        ArmyColor next = players.get(turn).getColor();

        if (piece != null && piece.getColor() == next) {

            moves.addAll(piece.getMoves(i, j, this));

        }
        
    }

    public ArmyColor nextColorToMove() {
        return players.get(turn).getColor();
    }
    
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

    
}
