/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.players.ai;

import java.util.List;
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
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.players.ArmyColor;

/**
 *
 * @author Simo
 */
public class AiPlayerGameplayTest {
    
    private Battle battle;
    private BattleMap map;
    private AiPlayer black;
    private AiPlayer white;
    public AiPlayerGameplayTest() {
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
        
        map.setPiece(2, 9, new King(ArmyColor.WHITE));
        map.setPiece(4, 9, new King(ArmyColor.BLACK));
        
        map.setPiece(2, 2, new Rook(ArmyColor.WHITE));
        map.setPiece(6, 2, new Rook(ArmyColor.BLACK));
        
        black = new AiPlayer(4, ArmyColor.BLACK, battle);
        white = new AiPlayer(4, ArmyColor.WHITE, battle);
        
        battle.addPlayer(black);
        battle.addPlayer(white);
        
        black.setUpTeam();
        white.setUpTeam();
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Tämä testi tehtiin testaamaan tekoälystä löytynyttä bugia.
     */
    @Test 
    public void blackRookEatsWhiteRook() {
        Move best = black.alphaBeta();
        assertFalse(best.getEaten() == null);
        assertEquals("White_Rook", best.getEaten().toString());
    }
    
    
    @Test
    public void blackRookCanEatWhiteRook() {
        
        Piece blackRook = map.getPiece(6, 2);
        List<Move> moves = blackRook.getMoves(6, 2, battle);
        boolean found = false;
        for (Move i : moves) {
            Piece eaten = i.getEaten();
            if (eaten == null) {
                continue;
            }
            found |= "White_Rook".equals(eaten.toString());
            
        }
        assertTrue(found);
    }
    
    


    
    
    
}
