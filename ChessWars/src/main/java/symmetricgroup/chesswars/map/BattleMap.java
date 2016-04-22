package symmetricgroup.chesswars.map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import symmetricgroup.chesswars.battle.move.Move;
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
import symmetricgroup.chesswars.ui.editor.map.MapEditorPanel;
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

    
    
    /**
     * Konstruktori luo taistelussa käytetyn kartan.
     * @param width kartan leveys
     * @param height kartan korkeus
     */
    public BattleMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Piece[height][width];
        this.mapTerrain = new Terrain[height][width];

        init();
        
    }
    
    

    
    private void init() {
        

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                mapTerrain[j][i] = new Plain();
                map[j][i] = null;

            }
        }

    }
    
    
    /**
     * Metodi palauttaa kartalla olevan nappulan tai null
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @return nappula tai null
     */
    public Piece getPiece(int x, int y) {
        return map[y][x];
    }
    /**
     * Metodi palauttaa kartalla olevan maaston
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @return palauttaa pisteen maaston
     */
    public Terrain getTerrain(int x, int y) {
        return mapTerrain[y][x];
    }
    
    /**
     * Metodi kertoo onko piste (x, y) kartan sisällä
     * @param   x X-koordinaatti
     * @param   y Y-koordinaatti
     * 
 
     * @return totuusarvo
     */
    public boolean isInside(int x, int y) {
        if (x < 0 || x >= width) {
            return false;
        }
        if (y < 0 || y >= height) {
            return false;
        }
        return true;
    }
    
    
    /**
     * Metodi toteuttaa siirron kartalla.
     * @param move toteutettava siirto
     */
    public void doMove(Move move) {

        map[move.getStartY()][move.getStartX()] = null;
        map[move.getEndY()][move.getEndX()] = move.getPiece();
        
    }
    
    /**
     * Metodi kumoaa siirron kartalla.
     * @param move kumottava siirto
     */
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

 
    /**
     * Metodi asettaa nappulan kartalle
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @param piece nappula
     */
    public void setPiece(int x, int y, Piece piece) {
        map[y][x] = piece;
    }
    
    /**
     * Metodi asettaa maaston kartalle
     * @param x maaston x-koordinaatti
     * @param y maaston y-koordinaatti
     * @param terrain maasto
     */
    public void setTerrain(int x, int y, Terrain terrain) {
        mapTerrain[y][x] = terrain;
    }
    
    /**
     * Metodi tyhjentää kartan.
     */
    public void clear() {
        init();
    }

    public void setMap(Piece[][] map) {
        this.map = map;
    }

    public void setMapTerrain(Terrain[][] mapTerrain) {
        this.mapTerrain = mapTerrain;
    }

}
