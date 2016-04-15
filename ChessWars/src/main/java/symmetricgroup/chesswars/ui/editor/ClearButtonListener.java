/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.ui.MapScreen;

/**
 * ClearButtonListener on ClearButton-napin kuuntelija.
 */
public class ClearButtonListener implements ActionListener {
    private BattleMap map;
    private MapScreen screen;

    public ClearButtonListener(BattleMap map, MapScreen screen) {
        this.map = map;
        this.screen = screen;
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        map.clear();
        screen.repaint();
    }
    
    
    
}
