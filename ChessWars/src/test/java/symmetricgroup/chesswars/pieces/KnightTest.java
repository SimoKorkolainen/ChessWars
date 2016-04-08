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
import symmetricgroup.chesswars.battle.Move;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.terrain.Mountains;

/**
 *
 * @author Simo
 */
public class KnightTest {
    Battle battle;
    BattleMap map;
    Knight knight;
    public KnightTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        map = new BattleMap(15, 15);
        battle = new Battle(map);
        knight = new Knight(ArmyColor.BLACK);
        map.setPiece(6, 6, knight);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void knightsColorIsCorrect() {
        assertEquals(knight.getColor(), ArmyColor.BLACK);
    }
    @Test
    public void knightsNameIsCorrect() {
        assertEquals(knight.getName(), "Knight");
    }
    
    @Test
    public void knightHasMoves() {
        assertFalse(knight.getMoves(6, 6, battle).isEmpty());
    }
    @Test
    public void knightCanMakeCorrectNumberOfMoves() {
        assertEquals(8, knight.getMoves(6, 6, battle).size());
    }
    @Test
    public void knightCantMoveOutsideMap() {
        
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                map.clear();
                map.setPiece(i, j, knight);
                for (Move m : knight.getMoves(i, j, battle)) {
                    assertTrue(map.isInside(m.getEndX(), m.getEndY()));
                }
            }
        }
    
    }
    
    @Test 
    public void knightIsBlockedByMountains() {
        map.setTerrain(8, 7, new Mountains());
        
        List<Move> moves = knight.getMoves(6, 6, battle);
        
        assertEquals(7, moves.size());
        
        for (Move m : moves) {
            
            assertFalse(m.getEndX() == 7 && m.getEndY() == 6);
            
        }
    
    }
    @Test
    public void knightCantMakeIncorrectMoves() {
        for (Move i : knight.getMoves(6, 6, battle)) {
            
            assertFalse(i.getEndX() == 6 && i.getEndY() == 6);

            int dx = Math.abs(i.getEndX() - 6);
            int dy = Math.abs(i.getEndY() - 6);

            assertTrue((dx == 2 && dy == 1) || (dx == 1 && dy == 2));

      
                    
        }
    
    
    }
    @Test 
    public void knightCanEatEnemyPawn() {
        
        map.setPiece(4, 5, new Pawn(ArmyColor.WHITE));
        
        boolean found = false;
        
        for (Move i : knight.getMoves(6, 6, battle)) {
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
    public void imageOfKnightIsNotNull() {
        assertFalse(knight.getImage() == null);
    }
    
    @Test
    public void copyWorks() {
        Piece copy = knight.copy();
        assertEquals(knight.getName(), copy.getName());
        assertEquals(knight.getColor(), copy.getColor());
    }
    
    @Test
    public void knightCanMoveCorrectNumberOfSteps() {
        assertEquals(1, knight.getMoveLength());
    }
    
}
