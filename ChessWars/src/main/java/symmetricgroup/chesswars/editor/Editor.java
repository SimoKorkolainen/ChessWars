/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.editor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import symmetricgroup.chesswars.map.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.ui.MapMouseListener;
import symmetricgroup.chesswars.ui.MapScreen;

/**
 *
 * @author Simo
 */
public class Editor extends JPanel {
    
    private BattleMap map;
    private MapEditor editor;
    private MapScreen screen;
    public Editor(BattleMap map) {
        this.map = map;
        
        createComponents();
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
        
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 1;
        constraints.gridy = 0;
        EditorPanel panel = new EditorPanel();
        super.add(panel, constraints);
        
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 1;
        constraints.gridy = 1;
        
        ClearButtonListener clearListener = new ClearButtonListener(map, screen);
        ClearButton clearButton = new ClearButton();
        clearButton.addActionListener(clearListener);
        
        super.add(clearButton, constraints);
        
        editor = new MapEditor(panel, map);
        MapMouseListener listener = new MapMouseListener(screen, null);
        listener.setEditor(editor);
        
        screen.addMouseListener(listener);
        
    }
}
