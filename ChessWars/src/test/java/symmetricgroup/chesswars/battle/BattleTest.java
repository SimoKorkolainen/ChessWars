/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.battle;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.pieces.Bishop;
import symmetricgroup.chesswars.pieces.Pawn;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.ai.AiPlayer;

/**
 *
 * @author Simo
 */
public class BattleTest {
    private Battle battle;
    private BattleMap map;
    public BattleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        map = new BattleMap(12, 9);
        battle = new Battle(map);
        
        map.setPiece(5, 5, new Rook(ArmyColor.WHITE));
        map.setPiece(7, 6, new Bishop(ArmyColor.BLACK));
        
        Move move = new Move(5, 5, 6, 5, map.getPiece(5, 5), null);
        battle.addPlayer(new AiPlayer(4, ArmyColor.WHITE, battle, false));
        battle.addPlayer(new AiPlayer(4, ArmyColor.BLACK, battle, false));
        battle.doMove(move);
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void turnAfterMoveIsOne() {
        assertEquals(1, battle.getTurn());
    }
    @Test
    public void rookMoves() {
        assertEquals("Rook", map.getPiece(6, 5).getName());
    }
    @Test
    public void movingRookLeavesEmptyBehind() {
        assertEquals(null, map.getPiece(5, 5));
    }
    
    @Test 
    public void undoMoveReturnsRookToItsOriginalPosition() {
        battle.undoLastMove();
        assertEquals("Rook", map.getPiece(5, 5).getName());
    }
    
    @Test 
    public void undoMoveLeavesEmptyBehind() {
        battle.undoLastMove();
        assertEquals(null, map.getPiece(6, 5));
    }
    
    @Test 
    public void afterUndoMoveTurnIsZero() {
        battle.undoLastMove();
        assertEquals(0, battle.getTurn());
    }
    
    @Test 
    public void rookIsRemovedWhenEaten() {
        eatRook();
        assertEquals("Bishop", map.getPiece(6, 5).getName());
    }
    
     @Test
     public void turnIsZeroAfterEatingRook() {
         eatRook();
         assertEquals(0, battle.getTurn());
     }
    
    @Test 
    public void eatingLeavesEmptyBehind() {
        eatRook();
        assertEquals(null, map.getPiece(7, 6));
    }
    @Test 
    public void undoEatingRestoresRook() {
        eatRook();
        battle.undoLastMove();
        assertEquals("Rook", map.getPiece(6, 5).getName());
    }
    @Test 
    public void undoEatingRestoresBishop() {
        eatRook();
        battle.undoLastMove();
        assertEquals("Bishop", map.getPiece(7, 6).getName());
    }
    
     @Test
     public void turnIsOneAfterUndoEating() {
         eatRook();
         battle.undoLastMove();
         assertEquals(1, battle.getTurn());
     }
    
    private void eatRook() {
        Move move = new Move(7, 6, 6, 5, map.getPiece(7, 6), map.getPiece(6, 5));
        battle.doMove(move);    
    }
}
