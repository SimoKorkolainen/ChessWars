/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.ui.editor.RoundRectImageButton;

/**
 * ColorSelectorButton on editorissa nappulan värin valitsemiseen tarkoitettu nappi.
 */
public class ColorSelectionButton extends RoundRectImageButton {
    private ArmyColor color;

    public ColorSelectionButton(ArmyColor color) {
        super(1, new BufferedImage(50, 50, BufferedImage.TYPE_4BYTE_ABGR_PRE));
        this.color = color;
        

        super.setOpaque(false);
        super.setBorderPainted(false);
        super.setActionCommand(color.toString());
        updateIcons();
        
    }

    public ArmyColor getColor() {
        return color;
    }
    

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
    
    public void updateIcons() {
        
        super.setPressedIcon(new ImageIcon(icon(lighten(color.getDrawingColor(), 0), lighten(color.getDrawingColor(), 30))));
        super.setIcon(new ImageIcon(icon(lighten(color.getDrawingColor(), -30), lighten(color.getDrawingColor(), 0))));
        super.setSelectedIcon(super.getPressedIcon());
    }
}

