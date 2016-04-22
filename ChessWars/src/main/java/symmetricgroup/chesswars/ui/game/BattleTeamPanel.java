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
import java.util.List;
import javax.swing.JPanel;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;
import symmetricgroup.chesswars.players.ai.AiPlayer;
import symmetricgroup.chesswars.ui.TextDrawer;
import symmetricgroup.chesswars.ui.editor.RoundRectTextButton;

/**
 *
 * @author Simo
 */
public class BattleTeamPanel extends JPanel {
    private int width;
    private int height;
    private Battle battle;

    public BattleTeamPanel(Battle battle) {
        this.battle = battle;
        this.width = 100;
        this.height = 20;
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



        drawer.draw("Teams:", x, y, g2d);

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);

            String name = player.getColor().toString();

            name += " " + battle.getTeam(player.getColor());


            x = width / 2;

            y = (i + 2) * height + height / 2;

            fill = RoundRectTextButton.lighten(player.getColor().getDrawingColor(), 50);
            drawer = new TextDrawer(fill,  height * 2 / 3);

            drawer.draw(name, x, y, g2d);
        }

    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height * (battle.getPlayers().size() + 3));
    }
    
}
