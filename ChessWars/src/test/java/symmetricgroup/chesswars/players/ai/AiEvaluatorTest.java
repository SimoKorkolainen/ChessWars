/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.players.ai;

import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.pieces.Bishop;
import symmetricgroup.chesswars.pieces.King;
import symmetricgroup.chesswars.pieces.Knight;
import symmetricgroup.chesswars.pieces.Pawn;
import symmetricgroup.chesswars.pieces.Queen;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.players.ArmyColor;

/**
 *
 * @author Simo
 */
public class AiEvaluatorTest {
    private BattleMap map;
    private Set<ArmyColor> myTeam;
    private final double ERROR_MARGIN = 0.001;
    public AiEvaluatorTest() {
        map = new BattleMap(10, 10);
        myTeam = new HashSet<>();
        myTeam.add(ArmyColor.WHITE);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void verticalDistanceFromOrigoIsCorrect() {
        assertEquals(AiEvaluator.distance(0, 0, 1, 0), 1, ERROR_MARGIN);
    }
    
    @Test
    public void horizontalDistanceFromOrigoIsCorrect() {
        assertEquals(AiEvaluator.distance(0, 0, 0, 1), 1, ERROR_MARGIN);
    }
    
    @Test
    public void diagonalDistanceFromOrigoIsCorrect() {
        assertEquals(AiEvaluator.distance(0, 0, 1, 1), Math.sqrt(2), ERROR_MARGIN);
    }
    
    @Test
    public void verticalDistanceIsCorrect() {
        assertEquals(AiEvaluator.distance(1, 0, 2, 0), 1, ERROR_MARGIN);
    }
    
    @Test
    public void horizontalDistanceIsCorrect() {
        assertEquals(AiEvaluator.distance(0, 1, 0, 2), 1, ERROR_MARGIN);
    }
    
    @Test
    public void diagonalDistanceCorrect() {
        assertEquals(AiEvaluator.distance(2, 2, 1, 1), Math.sqrt(2), ERROR_MARGIN);
    }
    
    @Test
    public void mapWithFriendlyRookIsEvaluatedCorrectly() {
        map.setPiece(3, 3, new Rook(ArmyColor.WHITE));
        assertEquals(AiEvaluator.ROOK_VALUE, AiEvaluator.evaluate(map, myTeam, false), ERROR_MARGIN);
    }
    
    @Test
    public void mapWithEnemyRookIsEvaluatedCorrectly() {
        
        map.setPiece(3, 3, new Rook(ArmyColor.BLACK));
        
        double eval = - (1 + AiEvaluator.AGGRESSIVENESS) * AiEvaluator.ROOK_VALUE;
        
        assertEquals(eval, AiEvaluator.evaluate(map, myTeam, false), ERROR_MARGIN);
    }
    
    @Test
    public void emptyMapIsEvaluatedCorrectly() {
        assertEquals(0, AiEvaluator.evaluate(map, myTeam, false), ERROR_MARGIN);
    }
    
    @Test
    public void randomnessIsAddedToEvaluation() {
        int n = 1000000;
        double mean = 0.5;
        double var = 0;
        for (int i = 0; i < n; i++) {
                
            double eval = AiEvaluator.evaluate(map, myTeam, true);
            
            double dx = eval / AiEvaluator.RANDOMNESS - mean;
            
            var += dx * dx;
        } 
        
        var /= n - 1;
       
        
        assertEquals(1.0 / 12, var, 0.1);
    }
    
    
    @Test
    public void friedlyKingIsEvaluatedCorrectly() {
        map.setPiece(3, 3, new King(ArmyColor.WHITE));
        
        double eval = AiEvaluator.KING_VALUE;
        
        assertEquals(eval, AiEvaluator.evaluate(map, myTeam, false), ERROR_MARGIN);
    }
    
    @Test
    public void enemyKingWithoutFriendlyPiecesIsEvaluatedCorrectly() {
        map.setPiece(3, 3, new King(ArmyColor.BLACK));
        
        double eval = - (1 + AiEvaluator.AGGRESSIVENESS) * AiEvaluator.KING_VALUE;
        
        assertEquals(eval, AiEvaluator.evaluate(map, myTeam, false), ERROR_MARGIN);
    }
    
    @Test
    public void enemyKingWithFriendlyPiecesIsEvaluatedCorrectly() {
        map.setPiece(3, 3, new King(ArmyColor.BLACK));
        map.setPiece(3, 6, new Rook(ArmyColor.WHITE));
        
        double eval = - (1 + AiEvaluator.AGGRESSIVENESS) * AiEvaluator.KING_VALUE;
        
        eval += AiEvaluator.ROOK_VALUE;
        
        double distToKing = 3.0 / (map.getWidth() + map.getHeight());
        
        eval -= distToKing * AiEvaluator.DIST_TO_KING_IMPORTANCE;
        
        assertEquals(eval, AiEvaluator.evaluate(map, myTeam, false), ERROR_MARGIN);
    }
    
    @Test
    public void friedlyBishopIsEvaluatedCorrectly() {
        map.setPiece(3, 3, new Bishop(ArmyColor.WHITE));
        
        double eval = AiEvaluator.BISHOP_VALUE;
        
        assertEquals(eval, AiEvaluator.evaluate(map, myTeam, false), ERROR_MARGIN);
    }
    
    @Test
    public void friedlyQueenIsEvaluatedCorrectly() {
        map.setPiece(3, 3, new Queen(ArmyColor.WHITE));
        
        double eval = AiEvaluator.QUEEN_VALUE;
        
        assertEquals(eval, AiEvaluator.evaluate(map, myTeam, false), ERROR_MARGIN);
    }
    
    @Test
    public void friedlyKnightIsEvaluatedCorrectly() {
        map.setPiece(3, 3, new Knight(ArmyColor.WHITE));
        
        double eval = AiEvaluator.KNIGHT_VALUE;
        
        assertEquals(eval, AiEvaluator.evaluate(map, myTeam, false), ERROR_MARGIN);
    }
    @Test
    public void friedlyPawnIsEvaluatedCorrectly() {
        map.setPiece(3, 3, new Pawn(ArmyColor.WHITE));
        
        double eval = AiEvaluator.PAWN_VALUE;
        
        assertEquals(eval, AiEvaluator.evaluate(map, myTeam, false), ERROR_MARGIN);
    }
}
