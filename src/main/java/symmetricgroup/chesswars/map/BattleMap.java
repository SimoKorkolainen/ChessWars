package symmetricgroup.chesswars.map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.ArrayList;
import symmetricgroup.chesswars.pieces.Piece;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author simokork
 */
public class BattleMap {
    private Map<ArmyColor, Integer> team;
    private List<Move> moves;
    private int width;
    private int height;
    private Piece[][] map; //Kartta käyttää matriisi-indeksointia

    public BattleMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Piece[width][height];
        this.team = new HashMap<>();
        this.moves = new ArrayList<>();
        init();

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
    }
    
    
    public void init() {
        
        setDefaultTeams();
        
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
            
                map[j][i] = null;
                
            }
        }
    
    
    }
    
    public int getTeam(ArmyColor color) {
        return team.get(color);
    }

    public Piece getPiece(int x, int y) {
        return map[y][x];
    }
    
    public boolean isInside(int x, int y) {
        if (x < 0 || x >= width) {
            return false;
        }
        if (y < 0 || y >= height) {
            return false;
        }
        return true;
    }
    
    
    
    public void doMove(Move move) {
        
        map[move.getStartY()][move.getEndY()] = null;
        
        map[move.getEndY()][move.getEndX()] = move.getPiece();
        
        moves.add(move);
    }
    
    private void undoMove(Move move) {
        
        map[move.getStartY()][move.getEndY()] = move.getPiece();
        
        map[move.getEndY()][move.getEndX()] = move.getEaten();
    
    }
    
    public void undoLastMove() {
        if (moves.isEmpty()) {
            return;
        }
        undoMove(moves.remove(moves.size() - 1));
    }
}
