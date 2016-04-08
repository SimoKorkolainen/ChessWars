/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
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
public class ImageColorerTest {
    public BufferedImage image;
    public ImageColorerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
        image.setRGB(0, 0, 0xffffffff);
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void coloringToRedWorks() {
        image = ImageColorer.color(image, new Color(255, 0, 0));
        assertEquals(0xffff0000, image.getRGB(0, 0));
    }
    
    @Test
    public void coloringToGreenWorks() {
        image = ImageColorer.color(image, new Color(0, 255, 0));
        assertEquals(0xff00ff00, image.getRGB(0, 0));
    }
    
    @Test
    public void coloringToBlueWorks() {
        image = ImageColorer.color(image, new Color(0, 0, 255));
        assertEquals(0xff0000ff, image.getRGB(0, 0));
    }
}
