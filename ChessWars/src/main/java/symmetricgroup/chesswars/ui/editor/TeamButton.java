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
 * TeamButton on pelaajien joukkueiden valitsemiseen käytetty nappi.
 */
public class TeamButton extends RoundRectTextButton {
    private ArmyColor color;
    private int team;
    private static final int MAX_TEAMS = 6;
    public TeamButton(ArmyColor color, int team) {
        super(30, 30, color.getDrawingColor(), new Color(30, 30, 30), "" + team);
        this.color = color;
        this.team = team;
        /*
        super.setOpaque(true);
        super.setBorderPainted(false);
        super.setBackground(color.getDrawingColor());
        */
    }

    public void incrementTeam() {
        team = team % MAX_TEAMS + 1;
        super.setButtonText("" + team);
    }
    
 
}
