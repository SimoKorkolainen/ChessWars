/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor.settings.teams;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import symmetricgroup.chesswars.ui.editor.settings.teams.TeamButton;

/**
 * TeamButtonListener on kuuntelija, 
 * jota käytetään pelaajien valitsemiseen.
 */
public class TeamButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        
        TeamButton button = (TeamButton) e.getSource();
        button.incrementTeam();
    }
    
}
