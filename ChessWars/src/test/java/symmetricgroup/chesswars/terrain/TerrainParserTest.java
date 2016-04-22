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
 * @author Simo
 */
public class TerrainParserTest {
    
    public TerrainParserTest() {
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
    public void parsingMountainsWork() {
        parsingTerrainWorks(new Mountains());
    }
    
    @Test
    public void parsingWoodsWork() {
        parsingTerrainWorks(new Woods());
    }
    
    @Test
    public void parsingPlainWork() {
        parsingTerrainWorks(new Plain());
    }
    
    @Test
    public void parsingMeaninglessTextReturnsNull() {
       assertEquals(null, TerrainParser.stringToTerrain("Moi oot paras!"));
    }
    
    public void parsingTerrainWorks(Terrain terrain) {
        Terrain other = TerrainParser.stringToTerrain(terrain.getName());
        assertEquals(terrain.getClass(), other.getClass());
    }
}
