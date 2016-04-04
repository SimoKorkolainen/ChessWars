/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.pieces.Bishop;
import symmetricgroup.chesswars.pieces.King;
import symmetricgroup.chesswars.pieces.Knight;
import symmetricgroup.chesswars.pieces.Pawn;
import symmetricgroup.chesswars.pieces.Queen;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.terrain.Mountains;
import symmetricgroup.chesswars.terrain.Plain;
import symmetricgroup.chesswars.terrain.Woods;

/**
 *
 * @author Simo
 */
public class MapEditorPanel extends JPanel {
    
    GridLayout layout;
    ArmyColor color;
    List<PieceSelectionButton> pieceButtons;
    List<ColorSelectionButton> colorButtons;
    List<TerrainSelectionButton> terrainButtons;
    
    ColorSelectionButton selectedColorButton;
    TerrainSelectionButton selectedTerrainButton;
    PieceSelectionButton selectedPieceButton;
    
    public MapEditorPanel() {
        layout = new GridLayout(5, 3);
        color = ArmyColor.WHITE;
        pieceButtons = new ArrayList<>();
        colorButtons = new ArrayList<>();
        terrainButtons = new ArrayList<>();
        createComponents();
    }
    public void createComponents() {
        super.setLayout(layout);
        
        colorButtons.add(new ColorSelectionButton(ArmyColor.WHITE));
        colorButtons.add(new ColorSelectionButton(ArmyColor.BLACK));
        colorButtons.add(new ColorSelectionButton(ArmyColor.YELLOW));
        colorButtons.add(new ColorSelectionButton(ArmyColor.RED));
        colorButtons.add(new ColorSelectionButton(ArmyColor.GREEN));
        colorButtons.add(new ColorSelectionButton(ArmyColor.BLUE));
        
        
        
        pieceButtons.add(new PieceSelectionButton(new Rook(color)));
        pieceButtons.add(new PieceSelectionButton(new Pawn(color)));
        pieceButtons.add(new PieceSelectionButton(new King(color)));
        pieceButtons.add(new PieceSelectionButton(new Queen(color)));
        pieceButtons.add(new PieceSelectionButton(new Bishop(color)));
        pieceButtons.add(new PieceSelectionButton(new Knight(color)));
        
        terrainButtons.add(new TerrainSelectionButton(new Mountains()));
        terrainButtons.add(new TerrainSelectionButton(new Woods()));
        terrainButtons.add(new TerrainSelectionButton(new Plain()));
        
        
        
        SelectionButtonListener listener = new SelectionButtonListener(this);
        
        for (ColorSelectionButton i : colorButtons) {
            i.addActionListener(listener);
            super.add(i);
        }
        for (PieceSelectionButton i : pieceButtons) {
            i.addActionListener(listener);
            super.add(i);
        }
        for (TerrainSelectionButton i : terrainButtons) {
            i.addActionListener(listener);
            super.add(i);
        }

        
        
    }
    public void setColor(ArmyColor color) {
        this.color = color;
        for (PieceSelectionButton i : pieceButtons) {
            i.setColor(color);
        }
    }

    public List<PieceSelectionButton> getPieceButtons() {
        return pieceButtons;
    }

    public List<ColorSelectionButton> getColorButtons() {
        return colorButtons;
    }

    public List<TerrainSelectionButton> getTerrainButtons() {
        return terrainButtons;
    }

    public ColorSelectionButton getSelectedColorButton() {
        return selectedColorButton;
    }

    public void setSelectedColorButton(ColorSelectionButton selectedColorButton) {
        this.selectedColorButton = selectedColorButton;
    }

    public TerrainSelectionButton getSelectedTerrainButton() {
        return selectedTerrainButton;
    }

    public void setSelectedTerrainButton(TerrainSelectionButton selectedTerrainButton) {
        this.selectedTerrainButton = selectedTerrainButton;
    }

    public PieceSelectionButton getSelectedPieceButton() {
        return selectedPieceButton;
    }

    public void setSelectedPieceButton(PieceSelectionButton selectedPieceButton) {
        this.selectedPieceButton = selectedPieceButton;
    }
    
    
}
