/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.ui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.ui.navigation.Navigation;
import symmetricgroup.chesswars.ui.navigation.Room;

/**
 * UserInterface on ohjelman koko käyttöliittymää hallinnoiva luokka.
 */
public class UserInterface implements Runnable {
    private JFrame frame;
    private Navigation navigation;

    public UserInterface() {
        navigation = new Navigation(this);
    }
    @Override
    public void run() {
        
        frame = new JFrame();
        
        
        frame.setPreferredSize(new Dimension(850, 800));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        
        Rook rook = new Rook(ArmyColor.WHITE);
        frame.setIconImage(rook.getImage());
        
        frame.setTitle("ChessWars");
        
        showRoom(navigation.getMainMenu());

        frame.setVisible(true);
        frame.pack();
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        
    }
    
    
 



    public JFrame getFrame() {
        return frame;
    }


    
    public void showRoom(Room room) {
        Container container = frame.getContentPane();
        container.removeAll();
        container.add(room);
        frame.revalidate();
        frame.repaint();
    }
}
