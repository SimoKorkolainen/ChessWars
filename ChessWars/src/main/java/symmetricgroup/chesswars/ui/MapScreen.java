/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.battle.Move;
import symmetricgroup.chesswars.players.SelectedPiece;
import symmetricgroup.chesswars.players.UserControl;
import symmetricgroup.chesswars.players.UserPlayer;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.terrain.Terrain;
import symmetricgroup.chesswars.util.ImageColorer;

/**
 *
 * @author simokork
 */
public class MapScreen extends JPanel {
    private BattleMap map;
    private Battle battle;
    private UserControl userControl;
    private int gridSize;
    
    public MapScreen(Battle battle, UserControl userControl) {
        super();
        this.battle = battle;
        this.userControl = userControl;
        this.map = battle.getMap();
        this.gridSize = 50;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        drawMap(g2d);
        if (userControl != null) {
            drawSelection(g2d);
        }
        Toolkit.getDefaultToolkit().sync();
    
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
            
            drawSelected(g2d, selected);
            drawSelectedMoves(g2d, selected);

        }
    }
    
    public void drawSelected(Graphics2D g2d, SelectedPiece selected) {
        int x = getMapX(selected.getX());
        int y = getMapY(selected.getY());
        
        g2d.setColor(Color.red);
        g2d.drawRect(x, y, gridSize, gridSize);
    }
    
    public void drawSelectedMoves(Graphics2D g2d, SelectedPiece selected) {

        Piece piece = selected.getPiece();
        
        for (Move i : piece.getMoves(selected.getX(), selected.getY(), battle)) {
            
            drawMove(g2d, i);

        }
    }
    
    public void drawMove(Graphics2D g2d, Move move) {
        int moveX = getMapX(move.getEndX());
        int moveY = getMapY(move.getEndY());

        g2d.setColor(Color.blue);
        g2d.drawRect(moveX, moveY, gridSize, gridSize);
    }
    
    public void drawPiece(Graphics2D g2d, int i, int j) {
        
        Piece piece = map.getPiece(i, j);
        
        if (piece != null) {
            
            int x = getMapX(i);
            int y = getMapY(j);
            piece.draw(g2d, x, y);
            
        }
        
    }

    public void drawTile(Graphics2D g2d, int i, int j) {
        int x = getMapX(i);
        int y = getMapY(j);

        g2d.setColor(getTileColor(i + j));
        
        g2d.fillRect(x, y, gridSize, gridSize);
    }
    
    public void drawTerrain(Graphics2D g2d, int i, int j) {

        int x = getMapX(i);
        int y = getMapY(j);

        Terrain terrain = map.getTerrain(i, j);
        
        terrain.draw(g2d, x, y);
    }
    
    public Color getTileColor(int n) {
        if (n % 2 == 0) {
            
            return new Color(0, 170, 0);
            
        }
        return new Color(0, 150, 0);
    }
    
    public int getMapX(int i) {
        return i * gridSize;
    }
    
    public int getMapY(int j) {
        return j * gridSize;
    }
    
    public int getGridX(int x) {
        return x / gridSize;
    }
    
    public int getGridY(int y) {
        return y / gridSize;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(map.getWidth() * gridSize, map.getHeight() * gridSize);
    }
    
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
    
}
