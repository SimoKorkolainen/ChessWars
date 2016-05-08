/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.ui.editor.map.TerrainSelectionButton;
import symmetricgroup.chesswars.ui.*;
/**
 * SelectionButtonListener on kartan muokkamiseen käytettyjen työkalujen valitsemisen hoitava kuuntelija.
 */
public class SelectionButtonListener implements ActionListener {
    private MapEditorPanel panel;

    public SelectionButtonListener(MapEditorPanel panel) {
        this.panel = panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

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
