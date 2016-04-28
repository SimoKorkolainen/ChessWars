/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor.map;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import symmetricgroup.chesswars.terrain.Terrain;
import symmetricgroup.chesswars.ui.editor.RoundRectImageButton;

/**
 * TerrainSelectionButton on editorissa maaston tyypin valitsemiseen käyttetty nappi.
 */
public class TerrainSelectionButton extends RoundRectImageButton {
    private Terrain terrain;

    public TerrainSelectionButton(Terrain terrain) {
        super(1, terrain.getImage());
        this.terrain = terrain;
        updateIcons();
    }

    public Terrain getTerrain() {
        return terrain;
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }
    
    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
    
    public void updateIcons() {
        
        super.setIcon(new ImageIcon(icon(lighten(Color.gray, 20), lighten(Color.gray, 40))));
        super.setPressedIcon(new ImageIcon(icon(lighten(Color.gray, 50), lighten(Color.gray, 70))));
        super.setSelectedIcon(new ImageIcon(icon(lighten(Color.gray, 50), lighten(Color.gray, 70))));
    }
    
}
