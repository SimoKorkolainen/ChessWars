/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.pieces;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import symmetricgroup.chesswars.players.ArmyColor;

/**
 *
 * @author Simo
 */
public class PieceParserTest {
    
    public PieceParserTest() {
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
    public void parsingRookWorks() {
        parsingPieceWorks(new Rook(ArmyColor.BLACK));
    }
    
    @Test
    public void parsingKnightWorks() {
        parsingPieceWorks(new Knight(ArmyColor.BLUE));
    }
    
    @Test
    public void parsingPawnWorks() {
        parsingPieceWorks(new Pawn(ArmyColor.GREEN));
    }
    
    @Test
    public void parsingQueenWorks() {
        parsingPieceWorks(new Queen(ArmyColor.RED));
    }
    
    @Test
    public void parsingKingWorks() {
        parsingPieceWorks(new Queen(ArmyColor.WHITE)); 
    }
    
    @Test
    public void parsingBishopWorks() {
        parsingPieceWorks(new Bishop(ArmyColor.WHITE)); 
    }
    
    public void parsingPieceWorks(Piece piece) {
        Piece other = PieceParser.stringToPiece(piece.toString());
        
        assertEquals(piece.getClass(), other.getClass());
        assertEquals(piece.getColor(), other.getColor());
    }
}
