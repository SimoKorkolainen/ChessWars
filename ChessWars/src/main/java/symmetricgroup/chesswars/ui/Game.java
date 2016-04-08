/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.battle.BattleThread;
import symmetricgroup.chesswars.players.Player;
import symmetricgroup.chesswars.players.ui.UserControl;
import symmetricgroup.chesswars.players.ui.UserPlayer;

/**
 *
 * @author simokork
 */
public class Game extends JPanel {
    private MapScreen screen;
    private Battle battle;
    UserControl control;

    public Game(Battle battle, UserControl control) {
        this.battle = battle;
        this.control = control;
        createComponents();
        
    }
    

    public void createComponents() {
        
        screen = new MapScreen(battle, control);
        
        MapMouseListener listener = new MapMouseListener(screen, control);
        

        
        screen.addMouseListener(listener);
        
        super.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        super.add(screen, constraints);
    
    }
    
}
