/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Simo
 */
public class MenuButtonListener implements ActionListener {
    public UserInterface ui;

    public MenuButtonListener(UserInterface ui) {
        this.ui = ui;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (command.equals(MainMenu.EDITORTEXT)) {
            System.out.println("Edit!");
            ui.showEditorMenu();
        }
        
        JFrame frame = ui.getFrame();
        Container container = frame.getContentPane();
        
        if (command.equals(MainMenu.PLAYTEXT)) {
            System.out.println("Play!");

            
            ui.showGame();

            
            
            
        }

        if (command.equals("Main menu")) {
            
            ui.showMainMenu();
        }
        
        if (command.contains("Map")) {
            ui.showEditor();
        }
    
    }
    
}
