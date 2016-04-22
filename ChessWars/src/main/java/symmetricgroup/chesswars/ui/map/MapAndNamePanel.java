/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.map;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import symmetricgroup.chesswars.ui.map.MapScreen;

/**
 *
 * @author Simo
 */
public class MapAndNamePanel extends JPanel {
    private MapScreen screen;
    private JLabel mapName;
    
    public MapAndNamePanel(MapScreen screen) {
        this.screen = screen;
        super.setOpaque(false);
        createComponents();
    }
    
    public void createComponents() {
        super.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.CENTER;

        constraints.insets = new Insets(5, 5, 5, 5);
        
        mapName = new JLabel("");
        mapName.setOpaque(false);
        mapName.setFont(new Font("SansSerif", Font.PLAIN, 60));
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        super.add(mapName, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        
        super.add(screen, constraints);
        
    }

    public MapScreen getScreen() {
        return screen;
    }

    public void setScreen(MapScreen screen) {
        this.screen = screen;
    }

    public JLabel getMapName() {
        return mapName;
    }

    public void setMapName(JLabel mapName) {
        this.mapName = mapName;
    }
    
    public void setMapName(String name) {
        mapName.setText(name);
    }
}
