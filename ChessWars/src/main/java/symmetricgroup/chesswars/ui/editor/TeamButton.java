/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor;

import java.awt.Color;
import javax.swing.JButton;
import symmetricgroup.chesswars.players.ArmyColor;

/**
 *
 * @author Simo
 */
public class TeamButton extends JButton {
    private ArmyColor color;
    private int team;
    private static final int MAX_TEAMS = 6;
    public TeamButton(ArmyColor color, int team) {
        super("" + team);
        this.color = color;
        this.team = team;
        super.setOpaque(true);
        super.setBorderPainted(false);
        super.setBackground(getButtonColor());
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
    
    public void incrementTeam() {
        team = team % MAX_TEAMS + 1;
        super.setText("" + team);
    }
}
