/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.util.ImageColorer;

/**
 *
 * @author simokork
 */
public class GameScreen extends JPanel {
    private BattleMap map;

    private int mapX;
    private int mapY;
    
    public GameScreen(BattleMap map) {
        super();
        this.map = map;
        mapX = 10;
        mapY = 10;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        

        map.drawMap(g2d, mapX, mapY);
        
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    
    }

    public int getMapX() {
        return mapX;
    }

    public int getMapY() {
        return mapY;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(map.getWidth() * map.getGridSize() + 50, map.getHeight() * map.getGridSize() + 50);
    }
    
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
    
}
