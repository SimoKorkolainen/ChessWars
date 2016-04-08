/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui;

import java.util.TimerTask;

/**
 *
 * @author Simo
 */
public class GameTimerManager extends TimerTask {

    private UserInterface ui;

    public GameTimerManager(UserInterface ui) {
        this.ui = ui;
    }
    
    
    
    @Override
    public void run() {
        if (ui.getFrame() != null) {
            ui.getFrame().repaint();
        }
    }
}
