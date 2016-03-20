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
public interface Player {
    public Move getMove(BattleMap map);
    public ArmyColor getColor();
}
