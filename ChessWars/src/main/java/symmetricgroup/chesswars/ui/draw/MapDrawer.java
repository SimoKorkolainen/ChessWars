/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.draw;

import symmetricgroup.chesswars.ui.map.MapScreen;
import java.awt.Color;
import java.awt.Graphics2D;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.players.ui.SelectedPiece;
import symmetricgroup.chesswars.players.ui.UserControl;
import symmetricgroup.chesswars.terrain.Terrain;

/**
 * MapDrawer-luokka sisältää metodit, joita kaytetään kartan piirtämiseen.
 */
public class MapDrawer {
    private BattleMap map;
    private Battle battle;
    private UserControl userControl;
    private MapScreen screen;
    
    public MapDrawer(Battle battle, UserControl userControl, MapScreen screen) {
        this.battle = battle;
        this.userControl = userControl;
        this.map = battle.getMap();
        this.screen = screen;
    }
    
    public void draw(Graphics2D g2d) {
        
        drawBackground(g2d, new Color(46, 55, 60));
        
        drawMap(g2d);
        if (userControl != null) {
            drawSelection(g2d);
        }
        
    }
    
    public void drawMap(Graphics2D g2d) {
        
        
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {

                drawTile(g2d, i, j);
                
                drawTerrain(g2d, i, j);
                
                drawPiece(g2d, i, j);

            }
        
        }

    }
    public void drawSelection(Graphics2D g2d) {
        SelectedPiece selected = userControl.getSelected();
        if (selected != null) {
            
            drawSelectedMoves(g2d, selected);

        }
    }
    

    
    public void drawSelectedMoves(Graphics2D g2d, SelectedPiece selected) {

        Piece piece = selected.getPiece();
        
        for (Move i : piece.getMoves(selected.getX(), selected.getY(), battle)) {
            
            drawMove(g2d, i);

        }
    }
    
    public void drawMove(Graphics2D g2d, Move move) {
        int border = screen.getGridSize() / 5;
        int size = screen.getGridSize() - 2 * border;
        
        int moveX = screen.getMapX(move.getEndX()) + border;
        int moveY = screen.getMapY(move.getEndY()) + border;

        g2d.setColor(new Color(0, 0, 0, 60));
        //g2d.drawRect(moveX, moveY, screen.getGridSize(), screen.getGridSize());
        g2d.fillRoundRect(moveX, moveY, size, size, 4, 4);
    }
    
    public void drawPiece(Graphics2D g2d, int i, int j) {
        
        Piece piece = map.getPiece(i, j);
        
        if (piece != null) {
            
            int x = screen.getMapX(i);
            int y = screen.getMapY(j);
            g2d.drawImage(piece.getImage(), x, y, null);
            
        }
        
    }
    
    public void drawBackground(Graphics2D g2d, Color color) {
        g2d.setColor(color);
        g2d.fillRect(0, 0, screen.getMapX(map.getWidth()), screen.getMapY(map.getHeight()));
    }

    public void drawTile(Graphics2D g2d, int i, int j) {
        
        int border = screen.getGridSize() / 50;
        
        int x = screen.getMapX(i);
        int y = screen.getMapY(j);
        
        int size = screen.getGridSize() - 2 * border;
        
        g2d.setColor(getRoundedTileColor(i + j));

        g2d.fillRoundRect(x + border, y + border, size, size, 4, 4);
    }
    
    public void drawTerrain(Graphics2D g2d, int i, int j) {

        int x = screen.getMapX(i);
        int y = screen.getMapY(j);

        Terrain terrain = map.getTerrain(i, j);
        
        g2d.drawImage(terrain.getImage(), x, y, null);
    }
    
    public Color getTileColor(int n) {
        if (n % 2 == 0) {
            
            return new Color(30, 180, 30);
            
        }
        return new Color(15, 160, 15);
    }
    
    public Color getRoundedTileColor(int n) {
        if (n % 2 == 0) {
            
            return new Color(113, 154, 78);
            
        }
        return new Color(187, 219, 144);
    }
    

}
