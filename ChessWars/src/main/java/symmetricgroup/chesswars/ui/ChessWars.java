package symmetricgroup.chesswars.ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.SwingUtilities;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.battle.BattleIO;
import symmetricgroup.chesswars.battle.Move;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.pieces.Bishop;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.ai.AiPlayer;
import symmetricgroup.chesswars.players.ui.UserControl;
import symmetricgroup.chesswars.ui.Game;
import symmetricgroup.chesswars.ui.MapScreen;
import symmetricgroup.chesswars.ui.GameTimer;
import symmetricgroup.chesswars.ui.UserInterface;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 * ChessWars on ohjelman käynnistävä pääluokka.
 */
public class ChessWars {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

       
        UserInterface ui = new UserInterface();
        
        GameTimer timer = new GameTimer(30, ui);
        
        SwingUtilities.invokeLater(ui);

    }
    
}
