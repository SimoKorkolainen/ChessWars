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
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.ai.AiPlayer;
import symmetricgroup.chesswars.players.ui.UserControl;

/**
 *
 * @author Simo
 */
public class BattleIOTest {
    private Battle battle;
    private BattleMap map;
    private Battle result;
    private final String filename = "battles/battleTest.txt";
    public BattleIOTest() {
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
        map.setPiece(0, 0, new Pawn(ArmyColor.WHITE));
        map.setPiece(5, 5, new Rook(ArmyColor.WHITE));
        map.setPiece(7, 6, new Bishop(ArmyColor.BLACK));
        map.setPiece(0, 8, new Pawn(ArmyColor.BLACK));
        map.setPiece(0, 1, new Rook(ArmyColor.WHITE));
        Move move = new Move(5, 5, 6, 5, map.getPiece(5, 5), null);
        battle.addPlayer(new AiPlayer(4, ArmyColor.WHITE, battle));
        battle.addPlayer(new AiPlayer(4, ArmyColor.BLACK, battle));
        battle.doMove(move);
        

        BattleIO.saveBattle(filename, battle);
        
        result = BattleIO.readBattle(filename, new UserControl());
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void turnIsPreserved() {
        assertEquals(battle.getTurn(), result.getTurn());
    }
    @Test
    public void numberOfMovesIsPreserved() {
        assertEquals(battle.getMoves().size(), result.getMoves().size());
    }
    @Test
    public void numberOfPlayersIsPreserved() {
        assertEquals(battle.getPlayers().size(), result.getPlayers().size());
    }
    
    @Test
    public void movesArePreserved() {
        
        for (int i = 0; i < battle.getMoves().size(); i++) {
            Move move = battle.getMoves().get(i);
            Move other = result.getMoves().get(i);
            
            assertEquals(move.getStartX(), other.getStartX());
            assertEquals(move.getStartY(), other.getStartY());
            assertEquals(move.getEndX(), other.getEndX());
            assertEquals(move.getEndY(), other.getEndY());
            
            assertPiecesAreSame(move.getEaten(), other.getEaten());
            assertPiecesAreSame(move.getPiece(), other.getPiece());
            
        }
    }
    
    public void assertPiecesAreSame(Piece piece, Piece other) {
        if(piece == null || other == null) {
            assertTrue(piece == null && other == null);
            return;
        }

        assertEquals(piece.getClass(), other.getClass());
        assertEquals(piece.getColor(), other.getColor());
    }  
}
