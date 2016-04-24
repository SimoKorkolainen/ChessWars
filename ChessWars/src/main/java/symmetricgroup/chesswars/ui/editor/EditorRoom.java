/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor;

import symmetricgroup.chesswars.ui.editor.map.MapEditor;
import symmetricgroup.chesswars.ui.map.MapAndNamePanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.ui.map.MapMouseListener;
import symmetricgroup.chesswars.ui.map.MapScreen;
import symmetricgroup.chesswars.ui.navigation.Navigation;
import symmetricgroup.chesswars.ui.navigation.Room;

/**
 * EditorRoom on editorin esittämiseen tarkoitettu luokka.
 */
public class EditorRoom extends Room {
    
 
    private EditorPanel editor;
    private MapAndNamePanel mapAndName;
    private SaveButton save;
    public EditorRoom(Navigation navigation, Battle battle) {
        super(navigation);
        
        createComponents(battle);
    }

    public void createComponents(Battle battle) {
        super.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 10, 10);
        

        
  
        
        MapScreen screen = new MapScreen(battle, null);


        
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        mapAndName = new MapAndNamePanel(screen);

        super.add(mapAndName, constraints);
        
        
        constraints.gridx = 1;
        constraints.gridy = 0;
        
        editor = new EditorPanel(this, battle, screen);
        
        super.add(editor, constraints);
        

        
    }

    public MapAndNamePanel getMapAndName() {
        return mapAndName;
    }

    public EditorPanel getEditorPanel() {
        return editor;
    }
    
    

    @Override
    public void update() {
    }
}
