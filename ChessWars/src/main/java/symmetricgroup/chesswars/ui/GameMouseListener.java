/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import symmetricgroup.chesswars.map.BattleMap;

/**
 *
 * @author Simo
 */
public class GameMouseListener implements MouseListener {
    private Game game;

    public GameMouseListener(Game game) {
        this.game = game;
    }


    
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        int relX = mouseX - game.getGameScreen().getMapX();
        int relY = mouseY - game.getGameScreen().getMapY();
        if (SwingUtilities.isLeftMouseButton(e)) {


            game.getMap().handleLeftMouseReleased(e, relX, relY);
        }
        if (SwingUtilities.isRightMouseButton(e)) {
            System.out.println("Right mouse released");
            game.getMap().handleRightMouseReleased(e, relX, relY);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
