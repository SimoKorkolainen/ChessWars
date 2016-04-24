/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.battle.defeat.BattleWinnerChecker;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;
import symmetricgroup.chesswars.players.ai.AiPlayer;
import symmetricgroup.chesswars.ui.TextDrawer;
import symmetricgroup.chesswars.ui.editor.RoundRectTextButton;

/**
 *
 * @author Simo
 */
public class BattleTurnPanel extends JPanel {
    private int width;
    private int height;
    private Battle battle;

    public BattleTurnPanel(Battle battle) {
        this.battle = battle;
        this.width = 200;
        this.height = 50;
        super.setDoubleBuffered(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        List<Player> players = battle.getPlayers();

        g2d.setColor(Color.LIGHT_GRAY);


        Color fill = RoundRectTextButton.lighten(ArmyColor.BLACK.getDrawingColor(), 50);

        TextDrawer drawer = new TextDrawer(fill, height * 2 / 3);

        int x = width / 2;

        int y = height;


        int turn = battle.getMoves().size() + 1;

        drawer.draw("Turn " + turn, x, y, g2d);


        if (BattleWinnerChecker.myTeamHasWon(battle, players.get(0).getColor())) {

            drawer.draw("Winners:", x, y + 3 * height / 2, g2d);

        } else {


            drawer.draw("Next:", x, y + 3 * height / 2, g2d);

        }

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get((i + battle.getTurn()) % players.size());

            String name = player.getColor().toString();

            if (player.getClass() == AiPlayer.class) {
                name += " AI";
            }



            x = width / 2;

            y = (i + 3) * height + height / 2;
            
            fill = player.getColor().getDrawingColor();
            if (player.getColor() == ArmyColor.WHITE) {
                fill = RoundRectTextButton.lighten(fill, 50);
            }
            drawer = new TextDrawer(fill,  height * 2 / 3);

            drawer.draw(name, x, y, g2d);
        }

    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height * (battle.getPlayers().size() + 4));
    }
    
}
