/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor.settings.teams;

import java.awt.Color;
import javax.swing.JButton;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.ui.editor.RoundRectTextButton;

/**
 * TeamButton on pelaajien joukkueiden valitsemiseen käytetty nappi.
 */
public class TeamButton extends RoundRectTextButton {
    private ArmyColor color;
    private int team;
    private static final int MAX_TEAMS = 6;
    private boolean playerIsIn;
    public TeamButton(ArmyColor color, int team) {
        super(30, 30, color.getDrawingColor(), new Color(30, 30, 30), "-");
        this.color = color;
        this.team = team;
        this.playerIsIn = false;
    }

    public void incrementTeam() {
        if (playerIsIn) {
            team = team % MAX_TEAMS + 1;
            super.setButtonText("" + team);
        }
    }

    public boolean isPlayerIn() {
        return playerIsIn;
    }

    public void setPlayerOut() {
        this.playerIsIn = false;
        super.setButtonText("-");
    }
    
    public void setPlayerIn() {
        this.playerIsIn = true;
        super.setButtonText("" + team);
    }

    public int getTeam() {
        return team;
    }
    
}
