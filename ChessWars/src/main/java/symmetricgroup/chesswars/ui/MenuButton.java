/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui;

import javax.swing.JButton;

/**
 *
 * @author Simo
 */
public class MenuButton extends JButton {
    
    public MenuButton(String text) {
        super();
        super.setText(text);
        super.setActionCommand(text);
    
    }
    
}
