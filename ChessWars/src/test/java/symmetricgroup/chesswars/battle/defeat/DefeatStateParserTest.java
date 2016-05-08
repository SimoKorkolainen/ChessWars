/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.battle.defeat;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import symmetricgroup.chesswars.battle.Battle;
import static org.junit.Assert.*;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.pieces.King;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;
import symmetricgroup.chesswars.players.ai.AiPlayer;
import symmetricgroup.chesswars.terrain.Mountains;
import symmetricgroup.chesswars.terrain.Woods;

/**
 *
 * @author Simo
 */
public class DefeatStateParserTest {
    private Battle battle;
    private BattleMap map;
    private List<Player> players;
    private Player white;
    private Player red;
    private Player green;
    private Player blue;
    private Player yellow;
    private Player black;
    
    public DefeatStateParserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        map = new BattleMap(10, 10);
        battle = new Battle(map);
        players = new ArrayList<>();
        putSomeTerrain();
        putSomeTroops();
        setUpPlayers();
    }
    
    @After
    public void tearDown() {
    }


    public void putSomeTerrain() {
        
        map.setTerrain(2, 2, new Mountains());
        map.setTerrain(1, 2, new Mountains());
        map.setTerrain(2, 1, new Mountains());
        map.setTerrain(3, 1, new Mountains());
        map.setTerrain(1, 3, new Mountains());
        map.setTerrain(4, 4, new Woods());
        map.setTerrain(5, 5, new Woods());  
    
    }
    
    public void putSomeTroops() {
        
        map.setPiece(0, 0, new King(ArmyColor.WHITE));
        map.setPiece(1, 0, new King(ArmyColor.BLACK));
        map.setPiece(0, 9, new King(ArmyColor.BLUE));
        map.setPiece(9, 9, new King(ArmyColor.RED));
        map.setPiece(5, 5, new King(ArmyColor.YELLOW));
        map.setPiece(6, 6, new King(ArmyColor.GREEN));
        
        map.setPiece(0, 2, new Rook(ArmyColor.BLUE));
        map.setPiece(5, 0, new Rook(ArmyColor.RED));
        map.setPiece(5, 4, new Rook(ArmyColor.BLACK));
        map.setPiece(6, 6, new Rook(ArmyColor.BLACK));
    }
    
    public void setUpPlayers() {
        white = new AiPlayer(2, ArmyColor.WHITE, battle, false);
        black = new AiPlayer(2, ArmyColor.BLACK, battle, false);
        blue = new AiPlayer(2, ArmyColor.BLUE, battle, false);
        red = new AiPlayer(2, ArmyColor.RED, battle, false);
        green = new AiPlayer(2, ArmyColor.GREEN, battle, false);
        yellow = new AiPlayer(2, ArmyColor.YELLOW, battle, false);      
        addPlayers();
    } 
    
    public void addPlayers() {
        battle.addPlayer(white);
        battle.addPlayer(black);
        battle.addPlayer(blue);
        battle.addPlayer(red);
        battle.addPlayer(green);
        battle.addPlayer(yellow);
        
        players = new ArrayList<>();
        
        for (Player i : battle.getPlayers()) {
            players.add(i);
        }
    }
    
    public void doWhitesFirstMove() {
        Move move = new Move(0, 0, 1, 0, map.getPiece(0, 0), map.getPiece(1, 0));
        battle.doMove(move);
    }
    
    @Test 
    public void playerIsParsedCorrectlyInBlacksDefeat() {

        
        doWhitesFirstMove();
        
        DefeatState defeat = getParsedLastDefeat(players);
        
        assertEquals(1, defeat.getPlayerPos());
        assertEquals(black, defeat.getPlayer());
    
    }
    
    
    @Test 
    public void removeMovesAreParsedCorrectlyInBlacksDefeat() {

        
        doWhitesFirstMove();
        
        DefeatState defeat = getParsedLastDefeat(players);
        List<DefeatState> states = battle.getDefeatStates();
        
        List<Move> old = states.get(states.size() - 1).getDefeatedRemoveMoves();
        
        List<Move> moves = defeat.getDefeatedRemoveMoves();
        
        assertEquals(old.size(), moves.size());
        
        for (int i = 0; i < moves.size(); i++) {
            movesAreSame(old.get(i), moves.get(i));
        }
    
    }
    
    public void movesAreSame(Move a, Move b) {
        assertEquals(a.getStartX(), b.getStartX());
        assertEquals(a.getEndX(), b.getEndX());
        
        
        piecesAreSame(a.getPiece(), b.getPiece());
        
        piecesAreSame(a.getEaten(), b.getEaten());
    }
    
    public void piecesAreSame(Piece a, Piece b) {
        if (a == null || b == null) {
            assertTrue(a == null && b == null);
            return;
        }
        assertEquals(a.toString(), b.toString());
    }
    
    public DefeatState getParsedLastDefeat(List<Player> players) {
        List<DefeatState> states = battle.getDefeatStates();
        String defeatConf = DefeatStateParser.defeatStateToString(states.get(states.size() - 1));
        
        return DefeatStateParser.stringToDefeatState(defeatConf, players);
        
        
    }
}
