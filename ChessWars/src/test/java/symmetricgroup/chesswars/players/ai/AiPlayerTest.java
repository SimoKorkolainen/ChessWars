/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.players.ai;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.battle.Move;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.pieces.King;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.terrain.Mountains;

/**
 *
 * @author Simo
 */
public class AiPlayerTest {
    private Battle battle;
    private BattleMap map;
    private AiPlayer black;
    private AiPlayer white;
    private AiPlayer red;
    public AiPlayerTest() {

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
        map.setPiece(0, 0, new King(ArmyColor.BLACK));
        map.setTerrain(1, 1, new Mountains());
        map.setPiece(1, 0, new Rook(ArmyColor.WHITE));
        map.setPiece(2, 0, new Rook(ArmyColor.RED));
        
        black = new AiPlayer(4, ArmyColor.BLACK, battle);
        white = new AiPlayer(4, ArmyColor.WHITE, battle);
        red = new AiPlayer(4, ArmyColor.RED, battle);
        
        battle.setTeam(ArmyColor.RED, battle.getTeam(ArmyColor.WHITE));
        
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void aiCalculatesMove() {
        setOrderWRB();
        white.calculateMove();
        assertTrue(white.moveIsReady());
    }
    
    @Test
    public void whiteEatsBlackKing() {
        setOrderWRB();
        white.calculateMove();
        Move best = white.getNextMove();
        assertEquals("King", best.getEaten().getName());
    }
    
    @Test
    public void blackKingMovesToSafety() {
        setOrderBRW();
        black.calculateMove();
        Move best = black.getNextMove();
        assertEquals(1, best.getEndX());
        assertEquals(0, best.getEndY());
    }
    
    public void setOrderWRB() {
        battle.addPlayer(white);
        battle.addPlayer(red);
        battle.addPlayer(black);
        setUpTeams();
    }
    
    public void setOrderBRW() {
        battle.addPlayer(black);
        battle.addPlayer(red);
        battle.addPlayer(white);
        setUpTeams();
    }
    
    public void setUpTeams() {
        white.setUpTeam();
        black.setUpTeam();
        red.setUpTeam();
    }
}
