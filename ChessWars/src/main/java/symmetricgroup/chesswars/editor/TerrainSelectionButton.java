/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.editor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import symmetricgroup.chesswars.terrain.Terrain;

/**
 *
 * @author Simo
 */
public class TerrainSelectionButton extends JToggleButton {
    private Terrain terrain;

    public TerrainSelectionButton(Terrain terrain) {
        super();
        this.terrain = terrain;
        super.setIcon(new ImageIcon(terrain.getImage()));
    }

    public Terrain getTerrain() {
        return terrain;
    }
    
    
}
