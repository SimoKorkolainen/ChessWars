/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import symmetricgroup.chesswars.ui.editor.settings.MapNameFieldListener;

/**
 * MapNameField on kartan nimen syöttämiseen käytetty tekstikenttä.
 */
public class MapNameField extends JPanel {

    private String mapName; 
    
    public MapNameField(String mapName, MapNameFieldListener listener) {
        this.mapName = mapName;
        super.setLayout(new GridLayout(2, 1));
        super.setOpaque(false);
        super.setBorder(new EmptyBorder(5, 5, 5, 5));
        createComponents(listener);
    }
    
    public void createComponents(MapNameFieldListener listener) {
        
        super.add(new JLabel("Battle name"));
        
        JTextField field = new JTextField(mapName);
        field.getDocument().addDocumentListener(listener);
        
        super.add(field);
    }
    
}
