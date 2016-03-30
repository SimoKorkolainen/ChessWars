/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import symmetricgroup.chesswars.editor.Editor;
import symmetricgroup.chesswars.editor.MapEditor;
import symmetricgroup.chesswars.map.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.map.Player;
import symmetricgroup.chesswars.map.UserControl;
import symmetricgroup.chesswars.map.UserPlayer;

/**
 *
 * @author Simo
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
            editor.handleLeftMouseReleased(gridX, gridY);
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
