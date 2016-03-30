package symmetricgroup.chesswars.pieces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import symmetricgroup.chesswars.map.ArmyColor;
import symmetricgroup.chesswars.map.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.map.Move;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.terrain.Mountains;

/**
 *
 * @author Simo
 */
public class RookTest {
    Battle battle;
    BattleMap map;
    Rook rook;
    public RookTest() {
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
        rook = new Rook(ArmyColor.BLACK);
        map.setPiece(6, 6, rook);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void rooksColorIsCorrect() {
        assertEquals(rook.getColor(), ArmyColor.BLACK);
    }
    @Test
    public void rooksNameIsCorrect() {
        assertEquals(rook.getName(), "Rook");
    }
    
    @Test
    public void rookHasMoves() {
        assertFalse(rook.getMoves(6, 6, battle).isEmpty());
    }
    @Test
    public void rookCanMakeCorrectNumberOfMoves() {
        assertEquals(24, rook.getMoves(6, 6, battle).size());
    }
    @Test
    public void rookCantMoveOutsideMap() {
        
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                map.clear();
                map.setPiece(i, j, rook);
                for (Move m : rook.getMoves(i, j, battle)) {
                    assertTrue(map.isInside(m.getEndX(), m.getEndY()));
                }
            }
        }
    
    }
    
    @Test 
    public void rookIsBlockedByMountains() {
        map.setTerrain(7, 6, new Mountains());
        
        List<Move> moves = rook.getMoves(6, 6, battle);
        
        assertEquals(18, moves.size());
        
        for (Move m : moves) {
            for (int i = 0; i < 6; i++) {
                assertFalse(m.getEndX() == (7 + i) && m.getEndY() == 6);
            }
        }
    
    }
    @Test
    public void rookCantMakeIncorrectMoves() {
        for (Move i : rook.getMoves(6, 6, battle)) {
            
            assertFalse(i.getEndX() == 6 && i.getEndY() == 6);
            assertFalse(i.getEndX() != 6 && i.getEndY() != 6);
            
            int dx = i.getEndX() - 6;
            int dy = i.getEndY() - 6;
            int dist = dx * dx + dy * dy;
            
            assertTrue(dist <= 6 * 6);
      
                    
        }
    
    
    }
    @Test 
    public void rookCanEatEnemyPawn() {
        
        map.setPiece(4, 6, new Pawn(ArmyColor.WHITE));
        
        boolean found = false;
        
        for (Move i : rook.getMoves(6, 6, battle)) {
            if (i.getEaten() == null) {
                continue;
            }
            if ("Pawn".equals(i.getEaten().getName())) {
                found = true;
            }
        }
        assertTrue(found);
    
    }
    
    @Test 
    public void imageOfRookIsNotNull() {
        assertFalse(rook.getImage() == null);
    }
    
    @Test
    public void copyWorks() {
        Piece copy = rook.copy();
        assertEquals(rook.getName(), copy.getName());
        assertEquals(rook.getColor(), copy.getColor());
    }
    
    @Test
    public void rookCanMoveCorrectNumberOfSteps() {
        assertEquals(6, rook.getMoveLength());
    }
    
}
