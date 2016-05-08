/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.navigation;

import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.players.ui.UserControl;
import symmetricgroup.chesswars.ui.game.Game;
import symmetricgroup.chesswars.ui.UserInterface;
import symmetricgroup.chesswars.ui.editor.EditorRoom;

/**
 *
 * @author Simo
 */
public class Navigation {
    private UserInterface ui;
    private NavigationRoom mainMenu;
    private NavigationRoom editorMenu;
    private NavigationRoom battleMenu;
    
    public Navigation(UserInterface ui) {
        this.ui = ui;
        createRooms();
        connectRooms();
        initRooms();
    }
   
    public void createRooms() {
        mainMenu = new NavigationRoom("ChessWars", "What do you want to do?", this);
        editorMenu = new NavigationRoom("Battle editor", "Please edit map", this);
        battleMenu = new NavigationRoom("Battle room", "Select map", this);
    }
    
    public void connectRooms() {
        connectMainMenu();
        connectEditorMenu();
        connectBattleMenu();
    }
    
    public void connectMainMenu() {
        mainMenu.addMenuButton(new MenuButton("Play", battleMenu));
        mainMenu.addMenuButton(new MenuButton("Edit battle", editorMenu));
    }
    
    
    public void connectEditorMenu() {
  
        editorMenu.addMenuButton(new MenuButton("Edit map", new EditorRoom(this, new Battle(new BattleMap(10, 10)))));
        editorMenu.addMenuButton(new MenuButton("Main menu", mainMenu));
    }
    
    public void connectBattleMenu() {
        UserControl control = new UserControl();

        Game game = new Game(this, new Battle(new BattleMap(10, 10)), control);
        String name = game.loadBattle().getMap().getMapName();
        battleMenu.addMenuButton(new MenuButton(name.trim(), game));

        battleMenu.addMenuButton(new MenuButton("Main menu", mainMenu));
        
    }
    
    
    public NavigationRoom getMainMenu() {
        return mainMenu;
    }

    public NavigationRoom getEditorMenu() {
        return editorMenu;
    }

    public void goToRoom(Room room) {
        update(room);
        room.update();
        ui.showRoom(room);
    }
    
    public void initRooms() {
        mainMenu.init();
        editorMenu.init();
        battleMenu.init();
    }

    public NavigationRoom getBattleMenu() {
        return battleMenu;
    }
    
    public void update(Room room) {
        if (room == battleMenu) {
            battleMenu.clearMenuButtons();
            connectBattleMenu();
        }
    }
    
    
}
