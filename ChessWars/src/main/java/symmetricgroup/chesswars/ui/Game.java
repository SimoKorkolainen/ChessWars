/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.ui;

/**
 *
 * @author simokork
 */
public class Game {
    private GameScreen gameScreen;

    
    public Game(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }
    
    
    
}
