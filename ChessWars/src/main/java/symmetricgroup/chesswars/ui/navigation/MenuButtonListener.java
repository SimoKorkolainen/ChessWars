/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.navigation;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * MenuButtonListener on MenuButton-napin kuuntelija.
 */
public class MenuButtonListener implements ActionListener {
    private Navigation navigation;

    public MenuButtonListener(Navigation navigation) {
        this.navigation = navigation;
    }


    
    @Override
    public void actionPerformed(ActionEvent e) {

        MenuButton button = (MenuButton) e.getSource();
        
        navigation.goToRoom(button.getRoom());
        
    }

    
}
