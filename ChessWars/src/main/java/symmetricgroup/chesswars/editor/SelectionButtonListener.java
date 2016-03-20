/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import symmetricgroup.chesswars.map.ArmyColor;

/**
 *
 * @author Simo
 */
public class SelectionButtonListener implements ActionListener {
    private EditorPanel panel;

    public SelectionButtonListener(EditorPanel panel) {
        this.panel = panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getSource().getClass() == ColorSelectionButton.class) {
            handleColorButtons(e);


        }
        if (e.getSource().getClass() == TerrainSelectionButton.class) {
            handleTerrainButton(e);
        }
        if (e.getSource().getClass() == PieceSelectionButton.class) {
            handlePieceButton(e);
        }
    
    }
    
    public void handleColorButtons(ActionEvent e) {
        
        ColorSelectionButton button = panel.getSelectedColorButton();
        
        if (button != null) {
            button.setSelected(false);
        }
        ColorSelectionButton selected = (ColorSelectionButton) e.getSource();
        selected.setSelected(true);
        panel.setColor(selected.getColor());
        panel.setSelectedColorButton(selected);
            
    }
    
    public void setMapObjectNotSelected() {
        PieceSelectionButton pieceButton = panel.getSelectedPieceButton();
        
        if (pieceButton != null) {
            pieceButton.setSelected(false);
        }
        
        TerrainSelectionButton terrainButton = panel.getSelectedTerrainButton();
        
        if (terrainButton != null) {
            terrainButton.setSelected(false);
        }
        
        panel.setSelectedTerrainButton(null);
        panel.setSelectedPieceButton(null);
        
    }
    public void handleTerrainButton(ActionEvent e) {
        setMapObjectNotSelected();
        TerrainSelectionButton selected = (TerrainSelectionButton) e.getSource();
        selected.setSelected(true);
        panel.setSelectedTerrainButton(selected);
    }
    
    public void handlePieceButton(ActionEvent e) {
        setMapObjectNotSelected();
        PieceSelectionButton selected = (PieceSelectionButton) e.getSource();
        selected.setSelected(true);
        panel.setSelectedPieceButton(selected);
    }
}
