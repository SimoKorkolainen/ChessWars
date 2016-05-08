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
import symmetricgroup.chesswars.battle.move.Move;
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
        map.setPiece(2, 0, new Rook(ArmyColor.WHITE));
        map.setPiece(3, 0, new Rook(ArmyColor.RED));
        map.setPiece(7, 0, new King(ArmyColor.WHITE));
        map.setPiece(7, 5, new King(ArmyColor.RED));
        
        black = new AiPlayer(4, ArmyColor.BLACK, battle, false);
        white = new AiPlayer(4, ArmyColor.WHITE, battle, false);
        red = new AiPlayer(4, ArmyColor.RED, battle, false);
        
        battle.setTeam(ArmyColor.RED, battle.getTeam(ArmyColor.WHITE));
        
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void aiCalculatesMove() {
        setOrderWRB();
        assertFalse(white.alphaBeta() == null);
    }
    
    @Test
    public void whiteEatsBlackKing() {
        setOrderWRB();
        Move best = white.alphaBeta();
        assertEquals("King", best.getEaten().getName());
    }
    
    @Test
    public void blackKingMovesToSafety() {
        setOrderBRW();
        

        Move best = black.alphaBeta();
        

        assertEquals(0, best.getEndX());
        assertEquals(1, best.getEndY());
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
