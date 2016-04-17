/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.ui.game;

import symmetricgroup.chesswars.ui.map.MapScreen;
import symmetricgroup.chesswars.ui.map.MapMouseListener;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.battle.BattleIO;
import symmetricgroup.chesswars.players.ui.UserControl;
import symmetricgroup.chesswars.ui.navigation.Navigation;
import symmetricgroup.chesswars.ui.navigation.Room;

/**
 * Game on pelin esittämiseen käytetty luokka.
 */
public class Game extends Room {
    private MapScreen screen;
    private Battle battle;
    UserControl control;

    public Game(Navigation navigation, Battle battle, UserControl control) {
        super(navigation);
        this.battle = battle;
        this.control = control;
        createComponents();
        super.setBackground(new Color(218, 231, 247));

    }
    

    public void createComponents() {
        
        screen = new MapScreen(battle, control);
        
        MapMouseListener listener = new MapMouseListener(screen, control);
        

        
        screen.addMouseListener(listener);
        
        super.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        super.add(screen, constraints);
        

        constraints.gridx = 1;
        constraints.gridy = 0;
        
        super.add(new BattleInfoPanel(battle), constraints);
    
    }

    @Override
    public void update() {
        battle = BattleIO.readBattle("battles/battleTest.txt", control);
        super.removeAll();
        createComponents();
        control.setBattle(battle);
        battle.start();
        
    }


    
}