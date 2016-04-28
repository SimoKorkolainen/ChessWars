/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor.settings.teams;

import symmetricgroup.chesswars.ui.editor.settings.teams.TeamButtonListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.ui.editor.EditorPanel;

/**
 * TeamSelectionPanel on paneeli,
 * joka sisältää pelaajien joukkueiden valitsemiseen käytetyt napit.
 */
public class TeamSelectionPanel extends JPanel {
    private HashMap<ArmyColor, TeamButton> teamButtons;
    
    public TeamSelectionPanel(EditorPanel editor) {
        this.teamButtons = new HashMap<>();
        super.setLayout(new GridLayout(2, 3));
        super.setOpaque(false);
        createComponents(editor);
    }
    
    public void createComponents(EditorPanel editor) {
        TeamButtonListener listener = new TeamButtonListener(editor);
        
        addButton(ArmyColor.WHITE, 1, listener);
        addButton(ArmyColor.BLACK, 2, listener);
        addButton(ArmyColor.YELLOW, 3, listener);
        addButton(ArmyColor.RED, 4, listener);
        addButton(ArmyColor.GREEN, 5, listener);
        addButton(ArmyColor.BLUE, 6, listener);

    }
    
    
    private void addButton(ArmyColor color, int team, TeamButtonListener listener) {
        TeamButton button = new TeamButton(color, team);
        button.addActionListener(listener);
        
        teamButtons.put(color, button);
        super.add(button);
    }
    
    public TeamButton getButton(ArmyColor color) {
        return teamButtons.get(color);
    }

    public HashMap<ArmyColor, TeamButton> getTeamButtons() {
        return teamButtons;
    }


    
}
