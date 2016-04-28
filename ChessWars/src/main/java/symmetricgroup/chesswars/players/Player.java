/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.players;

import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.ui.game.BattleMoveThread;

/**
 * Player on pelaajan toteuttama käyttöliittymä.
 */
public interface Player {
    
    /**
     * Metodi laskee ja toteuttaa siirron.
     * @param thread metodia kutsunut BattleMoveThread-olio
     */
    public void calculateAndExecuteMove(BattleMoveThread thread);
    
    /**
     * Metodi palauttaa pelaajan värin. Värin tulisi vastata pelaajaa yksi-yhteen.
     * @return pelaajan väri 
     */
    public ArmyColor getColor();

    public Player copy(Battle battle);
    
}
