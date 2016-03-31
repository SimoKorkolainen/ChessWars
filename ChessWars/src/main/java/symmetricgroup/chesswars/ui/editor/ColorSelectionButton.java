/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import symmetricgroup.chesswars.players.ArmyColor;

/**
 *
 * @author Simo
 */
public class ColorSelectionButton extends JToggleButton {
    private ArmyColor color;

    public ColorSelectionButton(ArmyColor color) {
        super();
        this.color = color;
        
        super.setBackground(getButtonColor());
        super.setOpaque(true);
        super.setBorderPainted(false);
        super.setActionCommand(color.toString());

        
    }

    public Color getButtonColor() {
        switch (color) {
            
            case WHITE: return Color.WHITE;
            case YELLOW: return Color.YELLOW;
            case BLACK: return Color.BLACK;
            case BLUE: return Color.BLUE;
            case GREEN: return Color.GREEN;
            case RED: return Color.RED;
                
            default: return null;
        }
    
    }

    public ArmyColor getColor() {
        return color;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }
    
    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
    
    
}
