/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import symmetricgroup.chesswars.ui.editor.settings.teams.TeamButton;

/**
 * SaveButton on editorissa luodun taistelun tallentamiseen käytetty nappi.
 */
public class SaveButton extends RoundRectTextButton {
    private EditorPanel editor;
    public SaveButton(EditorPanel editor) {
        super(30, 30, new Color(148, 199, 182), new Color(30, 30, 30), "Save");
        this.editor = editor;
        super.setEnabled(false);
  
    }

    public void updateSaveButton() {
        
        int teams = countTeams();
        if (teams >= 2) {
            super.setEnabled(true);
        } else {
            super.setEnabled(false);
        }
    }
    
    
    private int countTeams() {
        int count = 0;

        Collection<TeamButton> teamButtonCol = editor.getTeamPanel().getTeamButtons().values();
        List<TeamButton> teamButtons = new ArrayList(teamButtonCol);
        for (int i = 0; i < teamButtons.size(); i++) {
            if (!teamButtons.get(i).isPlayerIn()) {
                continue;
            }
            boolean found = false;
            for (int j = 0; j < i - 1; j++) {
                if (!teamButtons.get(j).isPlayerIn()) {
                    continue;
                }
                if (teamButtons.get(i).getTeam() == teamButtons.get(j).getTeam()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                count++;
            }
        }
        

        return count;
        
    }
    
}
