
package symmetricgroup.chesswars.ui.game;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.ui.RoundRectPanel;
import symmetricgroup.chesswars.ui.navigation.MenuButton;
import symmetricgroup.chesswars.ui.navigation.MenuButtonListener;
import symmetricgroup.chesswars.ui.navigation.Navigation;

/**
 *
 * @author Simo
 */
public class BattleInfoPanel extends JPanel {
    private Battle battle;

    public BattleInfoPanel(Battle battle, Navigation navigation) {
        this.battle = battle;
        super.setOpaque(false);
        createComponents(navigation);
    }
    public void createComponents(Navigation navigation) {
        

        
        super.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        
        
        super.add(new RoundRectPanel(new Color(158, 171, 187), new Color(188, 201, 217), 10, new BattleTurnPanel(battle)), constraints);
        

        if (battle.getPlayers().size() > 2) {
        
            constraints.gridx = 0;
            constraints.gridy = 1;

            super.add(new RoundRectPanel(new Color(158, 171, 187), new Color(188, 201, 217), 10, new BattleTeamPanel(battle)), constraints);
        }
        System.out.println(battle.getPlayers().size());
        constraints.gridx = 0;
        constraints.gridy = 2;
        
        MenuButton menuButton = new MenuButton("Battle menu", navigation.getBattleMenu());
        
        menuButton.addActionListener(new MenuButtonListener(navigation));
        
        super.add(menuButton, constraints);
    }
    
    
}
