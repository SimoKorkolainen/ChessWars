/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.Map;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Simo
 */
public class MainMenu extends JPanel {
    public static final String PLAYTEXT = "Play";
    public static final String EDITORTEXT = "Edit map";
    public static final String GAMENAME = "ChessWars";
            
    private MenuButton playButton;
    private MenuButton editorButton;
    
    public MainMenu(UserInterface ui) {
        createComponents(ui);
    
    }
    public void createComponents(UserInterface ui) {
        super.setLayout(new GridBagLayout());
        
        playButton = new MenuButton(PLAYTEXT);
        editorButton = new MenuButton(EDITORTEXT);
        
        
        MenuButtonListener listener = new MenuButtonListener(ui);
        


        
        playButton.addActionListener(listener);
        editorButton.addActionListener(listener);
        
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        super.add(playButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        super.add(editorButton, constraints);
        
        

        
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(hints);
        
        int width = super.getWidth();
        int height = super.getHeight();
        
        g2d.setFont(new Font("SansSerif", Font.PLAIN, Math.min(height / 10, width / 10)));
        
        FontMetrics metrics = g2d.getFontMetrics();
        
        int x = width / 2 - metrics.stringWidth(GAMENAME) / 2;
        int y = height / 3 - metrics.getHeight() / 2;
        
        g2d.drawString(GAMENAME, x, y);
    }
    
   
    
}
