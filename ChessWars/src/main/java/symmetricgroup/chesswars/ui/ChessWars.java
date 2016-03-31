package symmetricgroup.chesswars.ui;

import javax.swing.SwingUtilities;
import symmetricgroup.chesswars.ui.Game;
import symmetricgroup.chesswars.ui.MapScreen;
import symmetricgroup.chesswars.ui.GameTimer;
import symmetricgroup.chesswars.ui.UserInterface;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author simokork
 */
public class ChessWars {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        UserInterface ui = new UserInterface();
        
        GameTimer timer = new GameTimer(30, ui);
        
        SwingUtilities.invokeLater(ui);

    }
    
}
