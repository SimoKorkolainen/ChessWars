/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.ui;

import symmetricgroup.chesswars.map.BattleMap;

/**
 *
 * @author simokork
 */
public class Game {
    private GameScreen gameScreen;
    private BattleMap map;
    

    public Game(GameScreen gameScreen, BattleMap map) {
        this.gameScreen = gameScreen;
        this.map = map;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public BattleMap getMap() {
        return map;
    }
    
    
    
}
