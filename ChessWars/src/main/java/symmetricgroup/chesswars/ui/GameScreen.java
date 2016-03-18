/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author simokork
 */
public class GameScreen extends JPanel {
    
    public GameScreen() {
        super();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        

        try {
            
            BufferedImage rookBi = ImageIO.read(new File("WhiteRook.png"));
            Random r = new Random();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    int x = 50 + (i - 1) * rookBi.getWidth() + r.nextInt(10);
                    int y = 50 + (j - 1) * rookBi.getHeight() + r.nextInt(10);
                    g2d.drawImage(rookBi, x, y, null);
                }
            }
            
            
        } catch (Exception e) {
            System.out.println("Reading image failed!");
        }
        
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    
    }
    
    
}
