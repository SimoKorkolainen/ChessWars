/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AiButtonListener on tekoälyn valitsemiseen tarkoitetun napin kuuntelija.
 */
public class AiButtonListener implements ActionListener {

    
    @Override
    public void actionPerformed(ActionEvent e) {
        AiButton button = (AiButton) e.getSource();

        boolean ai = !button.isAiIsOn();
        
        button.setAiIsOn(ai);
        button.updateText();
        
    }
    
}
