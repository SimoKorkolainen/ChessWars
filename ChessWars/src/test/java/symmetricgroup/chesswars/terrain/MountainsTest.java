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
public class MountainsTest {
    private Mountains mountains;
    private Terrain copy;
    public MountainsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mountains = new Mountains();
        copy = mountains.copy();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void nameIsCorrect() {
        assertEquals("Mountains", mountains.getName());
    }
    
    @Test
    public void imageIsNotNull() {
        assertTrue(null != mountains.getImage());
    }
    
    @Test
    public void copyProducesCorrectClass() {
        assertTrue(copy.getClass() == Mountains.class);
    }
    
    @Test 
    public void moveCostIsCorrect() {
        assertEquals(6, mountains.moveCost());
    }
}
