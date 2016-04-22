/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor;

import symmetricgroup.chesswars.ui.editor.map.MapEditor;
import symmetricgroup.chesswars.ui.editor.map.MapEditorPanel;
import symmetricgroup.chesswars.ui.editor.settings.teams.TeamSelectionPanel;
import symmetricgroup.chesswars.ui.editor.settings.AiButtonPanel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.ui.map.MapMouseListener;
import symmetricgroup.chesswars.ui.editor.settings.MapNameFieldListener;
import symmetricgroup.chesswars.ui.map.MapScreen;
import symmetricgroup.chesswars.ui.navigation.MenuButton;
import symmetricgroup.chesswars.ui.navigation.MenuButtonListener;
import symmetricgroup.chesswars.ui.navigation.Navigation;

/**
 * EditorPanel on editorin työkalut sisältävä paneeli.
 */
public class EditorPanel extends JPanel {
    private Navigation navigation;
    private BattleMap map;
    private MapEditor editor;
    private MapScreen screen;
    private EditorRoom editorRoom;
    
    public EditorPanel(EditorRoom editorRoom, BattleMap map, MapScreen screen) {
        this.editorRoom = editorRoom;
        this.map = map;
        this.screen = screen;
        createComponents();
        super.setBackground(new Color(218, 231, 247));
        
   
    }

    public void createComponents() {
        super.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.insets = new Insets(5, 5, 5, 5);
  
        constraints.anchor = GridBagConstraints.CENTER;
        
        String name = "Best map ever";
        
        MapNameField mapName = new MapNameField(name, new MapNameFieldListener(this));
        editorRoom.getMapAndName().setMapName(name);
        
       
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        super.add(mapName, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        
        AiButtonPanel aiPanel = new AiButtonPanel();
        
        super.add(aiPanel, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;
        
        TeamSelectionPanel teamPanel = new TeamSelectionPanel();
        super.add(teamPanel, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 3;
        MapEditorPanel panel = new MapEditorPanel();
        super.add(panel, constraints);
        
        
        constraints.gridx = 0;
        constraints.gridy = 4;
        
        ClearButtonListener clearListener = new ClearButtonListener(map, screen);
        ClearButton clearButton = new ClearButton();
        clearButton.addActionListener(clearListener);
        
        super.add(clearButton, constraints);
        
        
        constraints.gridx = 0;
        constraints.gridy = 5;
        
        SaveButton save = new SaveButton();
        
        save.addActionListener(new SaveButtonListener(map));
        
        super.add(save, constraints);
        
        
        constraints.gridx = 0;
        constraints.gridy = 6;
        
        Navigation navi = editorRoom.getNavigation();
        
        MenuButton editorButton = new MenuButton("Editor menu", navi.getEditorMenu());
        
        editorButton.addActionListener(new MenuButtonListener(navi));
        
        super.add(editorButton, constraints);
        
        
        editor = new MapEditor(panel, map);
        MapMouseListener listener = new MapMouseListener(screen, null);
        listener.setEditor(editor);
        
        screen.addMouseListener(listener);
    }

    public EditorRoom getEditorRoom() {
        return editorRoom;
    }
    
    
}
