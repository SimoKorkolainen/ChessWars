/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import symmetricgroup.chesswars.players.ArmyColor;

/**
 * AiButtonPanel on paneeli, joka sisältää tekoälyn valitsemiseen tarkoitetut napit.
 */
public class AiButtonPanel extends JPanel {
    List<AiButton> buttons;
    JPanel grid;
    public AiButtonPanel() {
        
        buttons = new ArrayList<>();
        
        
        grid = new JPanel();
        grid.setLayout(new GridLayout(2, 3));
        super.setLayout(new GridBagLayout());
        
        createComponents();
        addComponents();
    }
    public void createComponents() {
        buttons.add(new AiButton(ArmyColor.WHITE));
        buttons.add(new AiButton(ArmyColor.BLACK));
        buttons.add(new AiButton(ArmyColor.YELLOW));
        buttons.add(new AiButton(ArmyColor.RED));
        buttons.add(new AiButton(ArmyColor.GREEN));
        buttons.add(new AiButton(ArmyColor.BLUE));
    }
    
    public void addComponents() {
        
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        JLabel aiLabel = new JLabel("Ai", SwingConstants.CENTER);
        
        aiLabel.setMaximumSize(new Dimension(150, 20));
        
        super.add(new JLabel("Ai", SwingConstants.CENTER), constraints);
        
        AiButtonListener listener = new AiButtonListener();
        for (AiButton i : buttons) {
            i.addActionListener(listener);
            grid.add(i);
        }
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        
        super.add(grid, constraints);
    }
}
