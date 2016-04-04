/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.ui.MapMouseListener;
import symmetricgroup.chesswars.ui.MapScreen;

/**
 *
 * @author Simo
 */
public class EditorPanel extends JPanel {
    private BattleMap map;
    private MapEditor editor;
    private MapScreen screen;
    public EditorPanel(BattleMap map, MapScreen screen) {
        this.map = map;
        this.screen = screen;
        createComponents();
    }

    public void createComponents() {
        super.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.insets = new Insets(10, 10, 10, 10);
  
        constraints.anchor = GridBagConstraints.CENTER;
        
        MapNameField mapName = new MapNameField("Best map ever");
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        super.add(mapName, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        
        TeamSelectionPanel teamPanel = new TeamSelectionPanel();
        super.add(teamPanel, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;
        MapEditorPanel panel = new MapEditorPanel();
        super.add(panel, constraints);
        
        
        constraints.gridx = 0;
        constraints.gridy = 3;
        
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
