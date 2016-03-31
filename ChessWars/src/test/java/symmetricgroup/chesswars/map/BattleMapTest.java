/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.map;

import symmetricgroup.chesswars.players.ArmyColor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import symmetricgroup.chesswars.pieces.Pawn;
import symmetricgroup.chesswars.pieces.Rook;

/**
 *
 * @author Simo
 */
public class BattleMapTest {
    BattleMap map;
    public BattleMapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        map = new BattleMap(12, 10);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void mapClearingWorks() {
        map.setPiece(5, 8, new Pawn(ArmyColor.WHITE));
        map.setPiece(11, 9, new Rook(ArmyColor.RED));
        map.clear();
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                assertEquals(null, map.getPiece(i, j));
                assertEquals("Plain", map.getTerrain(i, j).getName());
            }
        }
        
    }
    
    @Test 
    public void isInsideReturnsFalseWhenOutsideMap() {
        assertFalse(map.isInside(-1, 0));
        assertFalse(map.isInside(0, -1));
        assertFalse(map.isInside(0, map.getHeight()));
        assertFalse(map.isInside(0, map.getWidth()));
    }
    @Test 
    public void isInsideReturnsTrueWhenInsideMap() {
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                assertTrue(map.isInside(i, j));
            }
        }
    }
}
