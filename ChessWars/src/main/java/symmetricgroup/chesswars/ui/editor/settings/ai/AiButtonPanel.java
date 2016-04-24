/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor.settings.ai;

import symmetricgroup.chesswars.ui.editor.settings.AiButton;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.ui.editor.settings.teams.TeamButton;
import symmetricgroup.chesswars.ui.editor.settings.teams.TeamButtonListener;

/**
 * AiButtonPanel on paneeli, joka sisältää tekoälyn valitsemiseen tarkoitetut napit.
 */
public class AiButtonPanel extends JPanel {
    private HashMap<ArmyColor, AiButton> aiButtons;
    JPanel grid;
    public AiButtonPanel() {
        
        aiButtons = new HashMap<>();
        
        
        grid = new JPanel();
        grid.setLayout(new GridLayout(2, 3));
        super.setLayout(new GridBagLayout());
        
        createComponents();
        addComponents();
    }
    public void createComponents() {
        AiButtonListener listener = new AiButtonListener();
        addButton(ArmyColor.WHITE, listener);
        addButton(ArmyColor.BLACK, listener);
        addButton(ArmyColor.YELLOW, listener);
        addButton(ArmyColor.RED, listener);
        addButton(ArmyColor.GREEN, listener);
        addButton(ArmyColor.BLUE, listener);
    }
    
    public void addComponents() {
        
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        JLabel aiLabel = new JLabel("Ai", SwingConstants.CENTER);
        
        
        super.add(aiLabel, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        
        super.add(grid, constraints);
    }
    
    
    private void addButton(ArmyColor color, AiButtonListener listener) {
        AiButton button = new AiButton(color);
        button.addActionListener(listener);
        
        aiButtons.put(color, button);
        grid.add(button);
    }
    
    public AiButton getButton(ArmyColor color) {
        return aiButtons.get(color);
    }

    public HashMap<ArmyColor, AiButton> getAiButtons() {
        return aiButtons;
    }
    
    
}
