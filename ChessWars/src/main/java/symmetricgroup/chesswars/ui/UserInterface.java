/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.ui;

import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import symmetricgroup.chesswars.editor.EditorPanel;

/**
 *
 * @author simokork
 */
public class UserInterface implements Runnable {
    JFrame frame;
    Game game;
    public UserInterface(Game game) {
        this.game = game;
    }
    @Override
    public void run() {
        
        frame = new JFrame();
        frame.setVisible(true);
        
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        
        createContainer(frame.getContentPane());
        frame.pack();
    }
    
    public void createContainer(Container container) {
        container.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        game.getGameScreen().addMouseListener(new GameMouseListener(game));
        container.add(game.getGameScreen(), constraints);
        
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 1;
        constraints.gridy = 0;
        EditorPanel panel = new EditorPanel();
        game.getMap().setPanel(panel);
        container.add(panel, constraints);
    }
    
}
