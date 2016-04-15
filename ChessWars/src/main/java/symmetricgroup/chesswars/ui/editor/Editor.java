/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.ui.MapMouseListener;
import symmetricgroup.chesswars.ui.MapScreen;

/**
 * Editor on editorin esittämiseen tarkoitettu luokka.
 */
public class Editor extends JPanel {
    
    private BattleMap map;
    private MapEditor editor;
    private MapScreen screen;
    public Editor(BattleMap map) {
        this.map = map;
        
        createComponents();
        super.setBackground(new Color(218, 231, 247));
    }

    public void createComponents() {
        super.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        
        screen = new MapScreen(new Battle(map), null);
        
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        

        super.add(screen, constraints);
        
        constraints.anchor = GridBagConstraints.CENTER;
        
        constraints.gridx = 1;
        constraints.gridy = 0;
        
        EditorPanel editorPanel = new EditorPanel(map, screen);
        
        super.add(editorPanel, constraints);
    }
}
