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

/**
 *
 * @author Simo
 */
public class MapNameField extends JPanel {

    private String mapName; 
    
    public MapNameField(String mapName) {
        this.mapName = mapName;
        super.setLayout(new GridLayout(2, 1));
        super.setBorder(BorderFactory.createCompoundBorder(new EtchedBorder(), new EmptyBorder(10, 5, 10, 5)));
        
        createComponents();
    }
    
    public void createComponents() {
        
        super.add(new JLabel("Map name"));
        super.add(new JTextField(mapName));
    }
    
}
