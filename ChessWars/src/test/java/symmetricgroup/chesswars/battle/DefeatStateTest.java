/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.battle;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.pieces.King;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;
import symmetricgroup.chesswars.players.ai.AiPlayer;

/**
 *
 * @author Simo
 */
public class DefeatStateTest {
    Battle battle;
    BattleMap map;
    
    public DefeatStateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        map = new BattleMap(5, 5);
        battle = new Battle(map);
        
        battle.addPlayer(new AiPlayer(4, ArmyColor.BLACK, battle));
        battle.addPlayer(new AiPlayer(4, ArmyColor.WHITE, battle));
        battle.addPlayer(new AiPlayer(4, ArmyColor.RED, battle));
        
        map.setPiece(0, 0, new King(ArmyColor.BLACK));
        map.setPiece(0, 1, new Rook(ArmyColor.WHITE));
        map.setPiece(1, 0, new Rook(ArmyColor.BLACK));
        map.setPiece(0, 3, new King(ArmyColor.RED));
        
        Move move = new Move(1, 0, 2, 0, map.getPiece(1, 0), map.getPiece(2, 0));
        battle.doMove(move);
        
        move = new Move(0, 1, 0, 0, map.getPiece(0, 1), map.getPiece(0, 0));
        battle.doMove(move);
    }
    
    @After
    public void tearDown() {
        
        
    }
    @Test
    public void itsRedsTurnAfterBlacksDefeat() {
        assertEquals(ArmyColor.RED, battle.nextColorToMove());
    }
    
    @Test
    public void blackRookIsRemovedAfterDefeat() {
        assertEquals(null, map.getPiece(2, 0));
    }
    @Test
    public void blackPlayerIsRemovedAfterDefeat() {
        for (Player i : battle.getPlayers()) {
            assertFalse(i.getColor() == ArmyColor.BLACK);
        }
    }
    
    @Test
    public void defeatStateConstainsTheRemovedRook() {
        List<DefeatState> defeatStates = battle.getDefeatStates();
        
        DefeatState defeat = defeatStates.get(0);
        
        List<Move> defeated = defeat.getDefeatedRemoveMoves();
        
        assertEquals("Black_Rook", defeated.get(0).getEaten().toString());
    }
    
    @Test
    public void undoDefeatReturnsTheRemovedRook() {
        battle.undoLastMove();
        assertEquals("Black_Rook", map.getPiece(2, 0).toString());
    }
    
    @Test
    public void itsWhitesTurnAfterUndoDefeat() {
        battle.undoLastMove();
        assertEquals(ArmyColor.WHITE, battle.nextColorToMove());
    }
    
     @Test
    public void blackPlayerIsInCorrectPositionAfterUndoDefeat() {
        battle.undoLastMove();
        assertEquals(ArmyColor.BLACK, battle.getPlayers().get(0).getColor());
    }
}
