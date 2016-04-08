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
public class GameTimer {
    private Timer timer;

    public GameTimer(int fps, UserInterface ui) {

        timer = new Timer();
        timer.scheduleAtFixedRate(new GameTimerManager(ui), 100, fps);
    
    } 

    
}
