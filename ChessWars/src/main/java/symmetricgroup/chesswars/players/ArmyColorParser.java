/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.players;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Simo
 */
public class ArmyColorParser {
    private Map<String, ArmyColor> stringToArmyColor;
    /**
     * Konstruktori luo värin parsimiseen käytetyn olion.
     */
    public ArmyColorParser() {
        this.stringToArmyColor = new HashMap<>();
        init();
    }
    
    private void init() {
        ArmyColor[] ar = new ArmyColor[] {ArmyColor.BLACK, ArmyColor.BLUE, ArmyColor.GREEN,
                                          ArmyColor.RED, ArmyColor.WHITE, ArmyColor.YELLOW};
        for (ArmyColor i : ar) {
            
            stringToArmyColor.put(i.toString(), i);
        
        }
    }
    /**
     * Metodi muuttaa tekstin väriksi.
     * @param armyColor värin kuvaava teksti
     * @return tekstin kuvaama väri
     */
    public ArmyColor stringToArmyColor(String armyColor) {

        return stringToArmyColor.get(armyColor);
    }
}
