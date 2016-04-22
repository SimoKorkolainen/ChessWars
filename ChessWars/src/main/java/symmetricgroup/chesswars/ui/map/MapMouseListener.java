/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.map;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import symmetricgroup.chesswars.ui.editor.EditorRoom;
import symmetricgroup.chesswars.ui.editor.map.MapEditor;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.players.Player;
import symmetricgroup.chesswars.players.ui.UserControl;
import symmetricgroup.chesswars.players.ui.UserPlayer;

/**
 * MapMouseListener on hiiren kuuntelija kartan päällä.
 */
public class MapMouseListener implements MouseListener {
    private MapScreen screen;
    private MapEditor editor;
    private UserControl control;
    private int gridX;
    private int gridY;

    public MapMouseListener(MapScreen screen, UserControl control) {
        this.screen = screen;
        this.control = control;
    }




    
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        updateGridPosition(e);
        
        if (SwingUtilities.isLeftMouseButton(e)) {

            handleBattleLeftReleased();
            handleEditorLeftReleased();
            
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    public void handleBattleLeftReleased() {
        if (control != null) {
            control.handleLeftMouseReleased(gridX, gridY);
        }
        
        
    }
    public void handleEditorLeftReleased() {
        if (editor != null) {
            editor.addSelectedToMap(gridX, gridY);
            screen.repaint();
        }
    }
    
    public void updateGridPosition(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        gridX = screen.getGridX(mouseX);
        gridY = screen.getGridY(mouseY);
    }

    public void setEditor(MapEditor editor) {
        this.editor = editor;
    }
    
    
}
