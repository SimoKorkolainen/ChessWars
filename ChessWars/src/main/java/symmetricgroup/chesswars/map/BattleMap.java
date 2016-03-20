package symmetricgroup.chesswars.map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import symmetricgroup.chesswars.pieces.Piece;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import symmetricgroup.chesswars.pieces.Bishop;
import symmetricgroup.chesswars.pieces.Pawn;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.terrain.Mountains;
import symmetricgroup.chesswars.terrain.Plain;
import symmetricgroup.chesswars.terrain.Terrain;
import symmetricgroup.chesswars.terrain.Woods;
import symmetricgroup.chesswars.editor.EditorPanel;
import symmetricgroup.chesswars.editor.*;
import symmetricgroup.chesswars.pieces.King;
import symmetricgroup.chesswars.pieces.Knight;
import symmetricgroup.chesswars.pieces.Queen;
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
    private EditorPanel panel;
    
    private boolean selected;
    private int selectedX;
    private int selectedY;
    private int gridSize;
    private List<Player> players;
    private int turn;
    
    
    public BattleMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Piece[width][height];
        this.mapTerrain = new Terrain[width][height];
        this.team = new HashMap<>();
        this.moves = new ArrayList<>();
        this.selected = false; 
        this.selectedX = 0;
        this.selectedY = 0;
        this.gridSize = 50;
        this.players = new ArrayList<>();
        this.turn = 0;
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
        setTeam(ArmyColor.YELLOW, 6);
    }
    
    public void addPlayer(Player player) {
        
        players.add(player);
    
    }
    
    
    public void init() {
        
        setDefaultTeams();
        addPlayer(new UserPlayer(ArmyColor.WHITE));
        addPlayer(new UserPlayer(ArmyColor.BLACK));

        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                mapTerrain[j][i] = new Plain();
                map[j][i] = null;
                
                double rand = Math.random();
                if (rand < 0.8) {
                    
                    
                    
                    double randB = Math.random();
                    
                    if (randB < 0.8) {
                    
                    } else if (randB < 0.85) {
                        map[j][i] = new Pawn(ArmyColor.WHITE);
                    } else if (randB < 0.90) {
                        map[j][i] = new Pawn(ArmyColor.BLACK);
                    } else if (randB < 0.95) {
                        map[j][i] = new Rook(ArmyColor.WHITE);
                    } else {
                        map[j][i] = new Bishop(ArmyColor.BLACK);
                    }
                    
                    } else if (rand < 0.9) {
                        mapTerrain[j][i] = new Woods();
                    } else {
                        mapTerrain[j][i] = new Mountains();
                    }
                
            }
        }
    
        map[5][5] = new Rook(ArmyColor.WHITE);
        map[5][8] = new Rook(ArmyColor.BLACK);
        map[0][0] = new Pawn(ArmyColor.WHITE);
        //map[9][9] = new Pawn(ArmyColor.BLACK);
    }
    
    public int getTeam(ArmyColor color) {
        return team.get(color);
    }

    public Piece getPiece(int x, int y) {
        return map[y][x];
    }
    
    public Terrain getTerrain(int x, int y) {
        return mapTerrain[y][x];
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
        turn = (turn + 1) % players.size();
        
        if (getPiece(move.getEndX(), move.getEndY()) != move.getEaten()) {
            System.out.println("Move error3!! ");
        }
        if (getPiece(move.getStartX(), move.getStartY()) != move.getPiece()) {
            System.out.println("Move error4!! " + getPiece(move.getStartX(), move.getStartY()) + " " + move.getPiece());
        }

        map[move.getStartY()][move.getStartX()] = null;
        map[move.getEndY()][move.getEndX()] = move.getPiece();
        
        moves.add(move);
    }
    
    private void undoMove(Move move) {
        turn = (turn - 1 + players.size()) % players.size();
        map[move.getStartY()][move.getStartX()] = move.getPiece();
        
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
        if (Math.random() < 0.1) {
            ArmyColor color = players.get(turn).getColor();
            AiPlayer ai = new AiPlayer(5, color);
            Move move = ai.alphaBeta(this, 5);
            System.out.println("start x: " + move.getStartX() + " start y: " + move.getStartY());
            System.out.println("end x: " + move.getEndX() + " end y: " + move.getEndY());

            doMove(move);
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                
                Color color;
                if ((i + j) % 2 == 0) {
                    color = new Color(0, 170, 0);
                } else {
                    color = new Color(0, 150, 0);
                }
                
                int x = cornerX + i * gridSize;
                int y = cornerY + j * gridSize;
                
                g2d.setColor(color);
                g2d.fillRect(x, y, gridSize, gridSize);
                
                mapTerrain[j][i].draw(g2d, x, y);
                
                if (map[j][i] != null) {
                    map[j][i].draw(g2d, x, y);
                }
            }
        
        }
        
        if (selected) {
            int x = cornerX + selectedX * gridSize;
            int y = cornerY + selectedY * gridSize;
            g2d.setColor(Color.red);
            g2d.drawRect(x, y, gridSize, gridSize);
            
            for (Move i : map[selectedY][selectedX].getMoves(selectedX, selectedY, this)) {
                int moveX = cornerX + i.getEndX() * gridSize;
                int moveY = cornerY + i.getEndY() * gridSize;
                
                g2d.setColor(Color.blue);
                g2d.drawRect(moveX, moveY, gridSize, gridSize);
   
            }

            

            
            

        }
        
        g2d.setColor(Color.BLACK);
        
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 20));
        int x = cornerX + gridSize * width + 10;
        
        int y = cornerY + 20;
        g2d.drawString("Player: " + players.get(turn).getColor(), x, y);
    
    }

    public void handleLeftMouseReleased(MouseEvent e, int relX, int relY) {
        int x = relX / gridSize;
        int y = relY / gridSize;
        if (!selected) {


            if (isInside(x, y) && map[y][x] != null) {
                selected = true;
                selectedX = x;
                selectedY = y;
            }
        } else {
            if (map[selectedY][selectedX].getColor() == players.get(turn).getColor()) {


                boolean found = false;
                Move move = null;
                for (Move i : map[selectedY][selectedX].getMoves(selectedX, selectedY, this)) {

                    if (i.getEndX() == x && i.getEndY() == y) {
                        found = true;
                        move = i;
                        break;
                    }

                }
                if (found) {
                    doMove(move);
                    
                }
            }
            
            selected = false;
        }

    } 
   public void handleRightMouseReleased(MouseEvent e, int relX, int relY) {
        int x = relX / gridSize;
        int y = relY / gridSize;
        PieceSelectionButton pieceButton = panel.getSelectedPieceButton();
        TerrainSelectionButton terrainButton = panel.getSelectedTerrainButton();
        
        if (isInside(x, y)) {
            if (pieceButton != null) {

                Piece piece = pieceButton.getPiece();
                
                
                if (piece.getName().equals("Pawn")) {
                    map[y][x] = new Pawn(piece.getColor());
                }
                if (mapTerrain[y][x].getClass() != Mountains.class) {
                
                    switch (piece.getName()) {
                        case "Rook": map[y][x] = new Rook(piece.getColor());
                                     break;
                        case "Knight": map[y][x] = new Knight(piece.getColor());
                                       break;
                        case "Bishop": map[y][x] = new Bishop(piece.getColor());
                                     break;
                        case "Queen": map[y][x] = new Queen(piece.getColor());
                                      break;
                        case "King": map[y][x] = new King(piece.getColor());
                                     break;
                    }
                }

            }
            
            if (terrainButton != null) {
                
                Terrain terrain = terrainButton.getTerrain();
                map[y][x] = null;
                
                if (terrain.getClass() == Mountains.class) {
                    
                    mapTerrain[y][x] = new Mountains();
                    
                } else if (terrain.getClass() == Woods.class) {
                    
                    mapTerrain[y][x] = new Woods();
                
                } else {
                    
                    mapTerrain[y][x] = new Plain();
                    
                }
                
            }
        }
        
    }
    public List<Move> allPossibleNextMoves() {
        List<Move> all = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                
                if (map[j][i] != null && map[j][i].getColor() == players.get(turn).getColor()) {
                    
                    all.addAll(map[j][i].getMoves(i, j, this));
                
                }
                
            }
        
        }
    
        return all;
    }
    
    public ArmyColor nextColorToMove() {
        return players.get(turn).getColor();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setPanel(EditorPanel panel) {
        this.panel = panel;
    }
    
}
