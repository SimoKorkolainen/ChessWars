package symmetricgroup.chesswars.map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import symmetricgroup.chesswars.battle.Move;
import symmetricgroup.chesswars.players.ArmyColor;
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
import symmetricgroup.chesswars.ui.editor.MapEditorPanel;
import symmetricgroup.chesswars.pieces.King;
import symmetricgroup.chesswars.pieces.Knight;
import symmetricgroup.chesswars.pieces.Queen;
/**
 * BattleMap on luokka, joka sisältää kartan nappulat ja maaston.
 */
public class BattleMap {
    
    private int width;
    private int height;
    private Piece[][] map; //Kartta käyttää matriisi-indeksointia
    private Terrain[][] mapTerrain;

    
    
    
    public BattleMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Piece[height][width];
        this.mapTerrain = new Terrain[height][width];

        init();
        
    }
    
    

    
    public void init() {
        

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                mapTerrain[j][i] = new Plain();
                map[j][i] = null;

            }
        }

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

        map[move.getStartY()][move.getStartX()] = null;
        map[move.getEndY()][move.getEndX()] = move.getPiece();
        
    }
    
    public void undoMove(Move move) {
        
        map[move.getStartY()][move.getStartX()] = move.getPiece();
        
        map[move.getEndY()][move.getEndX()] = move.getEaten();
    
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

 

    public void setPiece(int x, int y, Piece piece) {
        map[y][x] = piece;
    }
    public void setTerrain(int x, int y, Terrain terrain) {
        mapTerrain[y][x] = terrain;
    }
    
    public void clear() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[j][i] = null;
                mapTerrain[j][i] = new Plain();
            }
        }
    
    }

    public void setMap(Piece[][] map) {
        this.map = map;
    }

    public void setMapTerrain(Terrain[][] mapTerrain) {
        this.mapTerrain = mapTerrain;
    }

}
