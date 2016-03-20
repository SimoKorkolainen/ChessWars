/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.map;

/**
 *
 * @author Simo
 */
public class UserPlayer implements Player {
    
    private ArmyColor color;

    public UserPlayer(ArmyColor color) {
        this.color = color;
    }
    
    

    @Override
    public Move getMove(BattleMap map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArmyColor getColor() {
        return color;
    }
    
}
