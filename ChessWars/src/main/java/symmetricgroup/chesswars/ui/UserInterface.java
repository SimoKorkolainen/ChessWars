/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.ui;

import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;

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
        
        frame.setPreferredSize(new Dimension(500, 400));
        createContainer(frame.getContentPane());
        frame.pack();
    }
    
    public void createContainer(Container container) {
        container.add(game.getGameScreen());
    }
    
}
