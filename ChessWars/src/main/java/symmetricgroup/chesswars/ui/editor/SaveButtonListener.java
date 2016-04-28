/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.battle.BattleIO;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;
import symmetricgroup.chesswars.players.ai.AiPlayer;
import symmetricgroup.chesswars.players.ui.UserControl;
import symmetricgroup.chesswars.players.ui.UserPlayer;
import symmetricgroup.chesswars.ui.editor.map.MapEditor;
import symmetricgroup.chesswars.ui.editor.settings.AiButton;
import symmetricgroup.chesswars.ui.editor.settings.ai.AiButtonPanel;
import symmetricgroup.chesswars.ui.editor.settings.teams.TeamSelectionPanel;

/**
 * SaveButtonListener on SaveButton-napin kuuntelija.
 */
public class SaveButtonListener implements ActionListener {
    private UserControl control;
    private Battle battle;
    private EditorRoom editor;

    public SaveButtonListener(Battle battle, EditorRoom room) {
        this.battle = battle;
        this.editor = room;
        this.control = new UserControl();
    }

    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Map<ArmyColor, AiButton> aiButtons = editor.getEditorPanel().getAiPanel().getAiButtons();
        TeamSelectionPanel teamPanel = editor.getEditorPanel().getTeamPanel();
        battle.setPlayers(new ArrayList<>());
        for (ArmyColor i : aiButtons.keySet()) {
            AiButton button = aiButtons.get(i);
            
            if (button.playerIsIn()) {
                ArmyColor color = button.getColor();
                int team = teamPanel.getButton(color).getTeam();
                System.out.println(color + " in team " + team);
                addPlayer(color, team, button.aiIsOn());
            
            }
        }
        
        battle.setName(editor.getMapAndName().getMapName().getText());
        System.out.println("Saved battle: " + battle.getName());
        
        SaveButton save = (SaveButton) e.getSource();
        save.updateSaveButton();
        BattleIO.saveBattle("battles/battleTest.txt", battle);
    }
    
    private void addPlayer(ArmyColor color, int team, boolean ai) {
        Player player;
        if (ai) {
            player = new AiPlayer(4, color, battle);
        } else {
            player = new UserPlayer(color, control);
        }
        battle.setTeam(color, team);
        battle.addPlayer(player);
    }
    
}
