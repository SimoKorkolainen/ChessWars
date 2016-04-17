/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.ui.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.battle.Move;
import symmetricgroup.chesswars.players.ui.SelectedPiece;
import symmetricgroup.chesswars.players.ui.UserControl;
import symmetricgroup.chesswars.players.ui.UserPlayer;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.terrain.Terrain;
import symmetricgroup.chesswars.ui.MapDrawer;
import symmetricgroup.chesswars.util.ImageColorer;

/**
 *
 * MapScreen on luokka, joka piirtää kartan paneelille.
 */
public class MapScreen extends JPanel {
    private MapDrawer mapDrawer;
    private Battle battle;
    private int gridSize;
    
    public MapScreen(Battle battle, UserControl userControl) {
        super();
        this.gridSize = 50;
        this.battle = battle;
        this.mapDrawer = new MapDrawer(battle, userControl, this);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(hints);
        
        mapDrawer.draw(g2d);

        Toolkit.getDefaultToolkit().sync();
    
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
        return new Dimension(battle.getMap().getWidth() * gridSize, battle.getMap().getHeight() * gridSize);
    }
    
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    public int getGridSize() {
        return gridSize;
    }
    
}
