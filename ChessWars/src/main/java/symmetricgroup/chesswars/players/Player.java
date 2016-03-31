/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.players;

import symmetricgroup.chesswars.battle.Move;

/**
 *
 * @author Simo
 */
public interface Player {
    
    public void calculateMove();
    public ArmyColor getColor();
    public boolean moveIsReady();
    public Move getNextMove();

    
}
