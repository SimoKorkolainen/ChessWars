package symmetricgroup.chesswars.map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import symmetricgroup.chesswars.pieces.Piece;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import symmetricgroup.chesswars.pieces.Pawn;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.terrain.Mountains;
import symmetricgroup.chesswars.terrain.Plain;
import symmetricgroup.chesswars.terrain.Terrain;
import symmetricgroup.chesswars.terrain.Woods;

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
    private Terrain[][] mapTerrain;
    
    public BattleMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Piece[width][height];
        this.mapTerrain = new Terrain[width][height];
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
                
                double rand = Math.random();
                if (rand < 0.8) {
                    mapTerrain[j][i] = new Plain();
                    
                    double randB = Math.random();
                    
                    if (randB < 0.8) {
                    
                    } else if (randB < 0.85) {
                        map[j][i] = new Pawn(ArmyColor.WHITE);
                    } else if (randB < 0.90) {
                        map[j][i] = new Pawn(ArmyColor.BLACK);
                    } else if (randB < 0.95) {
                        map[j][i] = new Rook(ArmyColor.RED);
                    } else {
                        map[j][i] = new Rook(ArmyColor.BLUE);
                    }
                    
                } else if (rand < 0.9) {
                    mapTerrain[j][i] = new Woods();
                } else {
                    mapTerrain[j][i] = new Mountains();
                }
            }
        }
    
    
    }
    
    public int getTeam(ArmyColor color) {
        return team.get(color);
    }

    public Piece getPiece(int x, int y) {
        return map[y][x];
    }
    
    public Terrain getTerrain(int x, int y) {
        return mapTerrain[x][y];
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

    public List<Move> getMoves() {
        return moves;
    }
    
    public void drawMap(Graphics2D g2d, int cornerX, int cornerY) {
    
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                
                Color color;
                if ((i + j) % 2 == 0) {
                    color = new Color(0, 200, 0);
                } else {
                    color = new Color(0, 150, 0);
                }
                
                int x = cornerX + i * 50;
                int y = cornerY + j * 50;
                
                g2d.setColor(color);
                g2d.fillRect(x, y, 50, 50);
                
                mapTerrain[j][i].draw(g2d, x, y);
                
                if (map[j][i] != null) {
                    map[j][i].draw(g2d, x, y);
                }
            }
        
        }
    
    }
}
