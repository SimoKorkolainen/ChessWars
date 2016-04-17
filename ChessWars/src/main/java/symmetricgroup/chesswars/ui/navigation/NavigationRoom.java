/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.navigation;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import symmetricgroup.chesswars.util.ImageColorer;
import symmetricgroup.chesswars.util.ImageLoader;

/**
 *
 * @author Simo
 */
public class NavigationRoom extends Room {
    private String roomName;
    private List<MenuButton> menuButtons;
    
    private BufferedImage largeRook;
    
    public NavigationRoom(String roomName, Navigation navigation) {
        super(navigation);
        this.menuButtons = new ArrayList<>();
        this.roomName = roomName;
        ImageLoader loader = new ImageLoader();
        largeRook = loader.loadImage("/images/largeWhiteRook.png");
        largeRook = ImageColorer.color(largeRook, new Color(187, 219, 144));
    }
    
    public void addMenuButton(MenuButton button) {
    
        menuButtons.add(button);
    
    }
    
    private void createComponents() {
        super.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);
        
        MenuButtonListener listener = new MenuButtonListener(getNavigation());
        int j = 0;
        
        for (MenuButton i : menuButtons) {
            constraints.gridx = 0;
            constraints.gridy = j;
            i.addActionListener(listener);
            super.add(i, constraints);
            j++;
        }
        
        

        
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(hints);
        
        int width = super.getWidth();
        int height = super.getHeight();
        
        
        g2d.setFont(new Font("SansSerif", Font.PLAIN, Math.min(height / 8, width / 8)));
        
        FontMetrics metrics = g2d.getFontMetrics();
        
        g2d.drawImage(largeRook, width / 2 - largeRook.getWidth() / 2, height / 2 - largeRook.getHeight() / 2, null);
        
        int x = width / 2 - metrics.stringWidth(roomName) / 2;
        int y = height / 3 - metrics.getHeight() / 2;
        
        g2d.setColor(new Color(46, 55, 60));
        g2d.drawString(roomName, x, y);
        
        
    }
    
    public void init() {
        createComponents();
    }

    @Override
    public void update() {
    }
    

}
