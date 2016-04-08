/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.terrain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author simokork
 */
public class WoodsTest {
    private Woods woods;
    private Terrain copy;
    public WoodsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        woods = new Woods();
        copy = woods.copy();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void nameIsCorrect() {
        assertEquals("Woods", woods.getName());
    }
    
    @Test
    public void imageIsNotNull() {
        assertTrue(null != woods.getImage());
    }
    
    @Test
    public void copyProducesCorrectClass() {
        assertTrue(copy.getClass() == Woods.class);
    }
    
    @Test 
    public void moveCostIsCorrect() {
        assertEquals(2, woods.moveCost());
    }
}
