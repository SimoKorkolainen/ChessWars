/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.navigation;

import java.awt.Color;
import javax.swing.JPanel;
import symmetricgroup.chesswars.ui.navigation.Navigation;

/**
 *
 * @author Simo
 */
public abstract class Room extends JPanel {
    private Navigation navigation;
    
    public Room(Navigation navigation) {
        this.navigation = navigation;
        super.setBackground(new Color(218, 231, 247));
    }
    
    public abstract void update();

    public Navigation getNavigation() {
        return navigation;
    }
    
    
}
