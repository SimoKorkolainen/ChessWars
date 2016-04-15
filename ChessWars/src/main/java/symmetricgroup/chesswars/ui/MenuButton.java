/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui;

import java.awt.Color;
import javax.swing.JButton;
import symmetricgroup.chesswars.ui.editor.RoundRectTextButton;

/**
 *MenuButton on nappi, jonka avulla on mahdollista navigoida käyttöliittymässä.
 */
public class MenuButton extends RoundRectTextButton {
    
    public MenuButton(String text) {
        super(40, 40, new Color(148, 199, 182), new Color(30, 30, 30), text);
        super.setActionCommand(text);
    
    }
    
}
