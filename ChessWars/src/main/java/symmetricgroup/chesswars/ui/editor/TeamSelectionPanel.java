/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import symmetricgroup.chesswars.players.ArmyColor;

/**
 * TeamSelectionPanel on paneeli,
 * joka sisältää pelaajien joukkueiden valitsemiseen käytetyt napit.
 */
public class TeamSelectionPanel extends JPanel {

    private List<TeamButton> teamButtons;
    
    public TeamSelectionPanel() {
        this.teamButtons = new ArrayList<>();
        super.setLayout(new GridLayout(2, 3));
        createComponents();
    }
    
    public void createComponents() {
        
        teamButtons.add(new TeamButton(ArmyColor.WHITE, 1));
        teamButtons.add(new TeamButton(ArmyColor.BLACK, 2));
        teamButtons.add(new TeamButton(ArmyColor.YELLOW, 3));
        teamButtons.add(new TeamButton(ArmyColor.RED, 4));
        teamButtons.add(new TeamButton(ArmyColor.GREEN, 5));
        teamButtons.add(new TeamButton(ArmyColor.BLUE, 6));
        
        TeamButtonListener listener = new TeamButtonListener();
                
        
        for (TeamButton i : teamButtons) {
            i.addActionListener(listener);
            super.add(i);
        }
    }
    


}
