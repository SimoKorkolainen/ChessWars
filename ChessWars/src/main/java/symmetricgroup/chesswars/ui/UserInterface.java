/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.ui;

import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import symmetricgroup.chesswars.ui.editor.Editor;
import symmetricgroup.chesswars.ui.editor.MapEditorPanel;
import symmetricgroup.chesswars.players.ai.AiPlayer;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.battle.BattleThread;
import symmetricgroup.chesswars.players.ui.UserControl;
import symmetricgroup.chesswars.players.ui.UserPlayer;
import symmetricgroup.chesswars.pieces.Rook;

/**
 *
 * @author simokork
 */
public class UserInterface implements Runnable {
    private JFrame frame;

    public UserInterface() {
    }
    @Override
    public void run() {
        
        frame = new JFrame();
        frame.setVisible(true);
        
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        Rook rook = new Rook(ArmyColor.WHITE);
        frame.setIconImage(rook.getImage());
        
        showMainMenu();
        
        frame.pack();
    }
    
    
 
    public void showEditorMenu() {
        Container container = frame.getContentPane();
        container.removeAll();
        container.add(new EditorMenu(this));
        frame.revalidate();
        frame.repaint();
    
    }
    
    public void showMainMenu() {
        Container container = frame.getContentPane();
        container.removeAll();
        container.add(new MainMenu(this));
        frame.revalidate();
        frame.repaint();
        
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public void showEditor() {
        Container container = frame.getContentPane();
        container.removeAll();
        container.add(new Editor(new BattleMap(10, 10)));
        frame.revalidate();
        frame.repaint();
    }
    
    public void showGame()  {
        Container container = frame.getContentPane();
        container.removeAll();
        BattleMap map = new BattleMap(10, 10);
        map.putSomeTroops();
        Battle battle = new Battle(map);
        
        UserControl control = new UserControl(battle);
        
        //UserPlayer white = new UserPlayer(ArmyColor.WHITE, control);
        AiPlayer white = new AiPlayer(4, ArmyColor.WHITE, battle);
        AiPlayer black = new AiPlayer(4, ArmyColor.BLACK, battle);
        battle.addPlayer(white);
        battle.addPlayer(black);
        
        battle.start();
        
        
        
        container.add(new Game(battle, control));
        frame.revalidate();
        frame.repaint();
    }
    
}
