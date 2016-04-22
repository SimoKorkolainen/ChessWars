/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.pieces;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.terrain.Mountains;

/**
 *
 * @author Simo
 */
public class KingTest {
    
    Battle battle;
    BattleMap map;
    King king;
    public KingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        map = new BattleMap(13, 13);
        battle = new Battle(map);
        king = new King(ArmyColor.WHITE);
        map.setPiece(6, 6, king);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void kingsColorIsCorrect() {
        assertEquals(king.getColor(), ArmyColor.WHITE);
    }
    @Test
    public void kingsNameIsCorrect() {
        assertEquals(king.getName(), "King");
    }
    
    @Test
    public void kingHasMoves() {
        assertFalse(king.getMoves(6, 6, battle).isEmpty());
    }
    @Test
    public void kingCanMakeCorrectNumberOfMoves() {
        assertEquals(8, king.getMoves(6, 6, battle).size());
    }
    @Test
    public void kingCantMoveOutsideMap() {
        
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                map.clear();
                map.setPiece(i, j, king);
                for (Move m : king.getMoves(i, j, battle)) {
                    assertTrue(map.isInside(m.getEndX(), m.getEndY()));
                }
            }
        }
    
    }
    
    @Test 
    public void rookIsBlockedByMountains() {
        map.setTerrain(7, 6, new Mountains());
        
        List<Move> moves = king.getMoves(6, 6, battle);
        
        assertEquals(7, moves.size());
        
        for (Move m : moves) {
            
            assertFalse(m.getEndX() == 7 && m.getEndY() == 6);
            
        }
    
    }
}
