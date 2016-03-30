/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.ui;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author simokork
 */
public class GameTimer extends TimerTask {
    private Timer timer;
    private int fps;
    private UserInterface ui;
    
    
    public GameTimer(int fps, UserInterface ui) {
        this.ui = ui;
        timer = new Timer();
        timer.scheduleAtFixedRate(this, 100, fps);
    
    } 

    @Override
    public void run() {
        if (ui.getFrame() != null) {
            ui.getFrame().repaint();
        }
    }
    
}
