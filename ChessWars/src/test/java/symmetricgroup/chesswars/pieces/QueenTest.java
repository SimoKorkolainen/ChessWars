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
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.terrain.Mountains;

/**
 *
 * @author Simo
 */
public class QueenTest {
    Battle battle;
    BattleMap map;
    Queen queen;
    public QueenTest() {
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
        queen = new Queen(ArmyColor.BLACK);
        map.setPiece(6, 6, queen);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void queensColorIsCorrect() {
        assertEquals(queen.getColor(), ArmyColor.BLACK);
    }
    @Test
    public void queensNameIsCorrect() {
        assertEquals(queen.getName(), "Queen");
    }
    
    @Test
    public void queenHasMoves() {
        assertFalse(queen.getMoves(6, 6, battle).isEmpty());
    }
    @Test
    public void queenCanMakeCorrectNumberOfMoves() {
        assertEquals(48, queen.getMoves(6, 6, battle).size());
    }
    @Test
    public void queenCantMoveOutsideMap() {
        
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                map.clear();
                map.setPiece(i, j, queen);
                for (Move m : queen.getMoves(i, j, battle)) {
                    assertTrue(map.isInside(m.getEndX(), m.getEndY()));
                }
            }
        }
    
    }
    
    @Test 
    public void queenIsBlockedByMountains() {
        map.setTerrain(7, 6, new Mountains());
        
        List<Move> moves = queen.getMoves(6, 6, battle);
        
        assertEquals(42, moves.size());
        
        for (Move m : moves) {
            for (int i = 0; i < 6; i++) {
                assertFalse(m.getEndX() == (7 + i) && m.getEndY() == 6);
            }
        }
    
    }
    @Test
    public void queenCantMakeIncorrectMoves() {
        for (Move i : queen.getMoves(6, 6, battle)) {
            
            assertFalse(i.getEndX() == 6 && i.getEndY() == 6);

            
            int dx = i.getEndX() - 6;
            int dy = i.getEndY() - 6;
 
            int absDx = Math.abs(dx);
            int absDy = Math.abs(dy);
            
            assertTrue(absDx == 0 || absDy == 0 || absDx == absDy);
            
            assertTrue(absDx <= 6 && absDy <= 6);
      
                    
        }
    
    
    }
    @Test 
    public void queenCanEatEnemyPawn() {
        
        map.setPiece(4, 6, new Pawn(ArmyColor.WHITE));
        
        boolean found = false;
        
        for (Move i : queen.getMoves(6, 6, battle)) {
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
    public void imageOfQueenIsNotNull() {
        assertFalse(queen.getImage() == null);
    }
    
    @Test
    public void copyWorks() {
        Piece copy = queen.copy();
        assertEquals(queen.getName(), copy.getName());
        assertEquals(queen.getColor(), copy.getColor());
    }
    
    @Test
    public void queenCanMoveCorrectNumberOfSteps() {
        assertEquals(6, queen.getMoveLength());
    }
    
}
