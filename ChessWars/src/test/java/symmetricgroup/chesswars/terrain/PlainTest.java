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
public class PlainTest {
    private Plain plain;
    private Terrain copy;
    public PlainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        plain = new Plain();
        copy = plain.copy();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void nameIsCorrect() {
        assertEquals("Plain", plain.getName());
    }
    
    @Test
    public void imageIsNotNull() {
        assertTrue(null != plain.getImage());
    }
    
    @Test
    public void copyProducesCorrectClass() {
        assertTrue(copy.getClass() == Plain.class);
    }
    
    @Test 
    public void moveCostIsCorrect() {
        assertEquals(1, plain.moveCost());
    }
}
