/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.battle.BattleIO;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.ai.AiPlayer;
import symmetricgroup.chesswars.players.ui.UserControl;
import symmetricgroup.chesswars.players.ui.UserPlayer;

/**
 * SaveButtonListener on SaveButton-napin kuuntelija.
 */
public class SaveButtonListener implements ActionListener {

    private Battle battle;

    public SaveButtonListener(BattleMap map) {
        battle = new Battle(map);
        battle.addPlayer(new AiPlayer(4, ArmyColor.WHITE, battle));
        battle.addPlayer(new UserPlayer(ArmyColor.BLACK, new UserControl()));
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        BattleIO.saveBattle("battles/battleTest.txt", battle);
    }
    
}
