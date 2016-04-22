/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import symmetricgroup.chesswars.pieces.Bishop;
import symmetricgroup.chesswars.pieces.King;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.pieces.Queen;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.terrain.Mountains;
import symmetricgroup.chesswars.terrain.Terrain;
import symmetricgroup.chesswars.terrain.Woods;

/**
 *
 * @author Simo
 */
public class MapCopierTest {
    private BattleMap map;
    public MapCopierTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        map = new BattleMap(10, 12);
        map.setPiece(3, 3, new Rook(ArmyColor.WHITE));
        map.setTerrain(4, 4, new Mountains());
        map.setTerrain(0, map.getHeight() - 1, new Woods());
        map.setTerrain(map.getWidth() - 1, 0, new Woods());
        map.setPiece(1, 3, new King(ArmyColor.BLACK));
        map.setPiece(0, 0, new Bishop(ArmyColor.BLACK));
        map.setPiece(map.getWidth() - 1, map.getHeight() - 1, new Queen(ArmyColor.WHITE));
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void copyHasCorrectSize() {
        BattleMap copy = MapCopier.copy(map);
        assertEquals(map.getWidth(), copy.getWidth());
        assertEquals(map.getHeight(), copy.getHeight());
    }
    
    @Test
    public void piecesAreCopied() {
        BattleMap copy = MapCopier.copy(map);
        for (int i = 0; i < copy.getWidth(); i++) {
            for (int j = 0; j < copy.getHeight(); j++) {
                Piece piece = map.getPiece(i, j);
                Piece copyPiece = copy.getPiece(i, j);
                
                if(piece == null || copyPiece == null) {
                    assertTrue(piece == null && copyPiece == null);
                    continue;
                }
                
                assertEquals(piece.getClass(), copyPiece.getClass());
                assertEquals(piece.getColor(), copyPiece.getColor());
            }
        }
    }
    
    @Test
    public void terrainsAreCopied() {
        BattleMap copy = MapCopier.copy(map);
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                Terrain terrain = map.getTerrain(i, j);
                Terrain copyTerrain = copy.getTerrain(i, j);
                assertEquals(terrain.getClass(), copyTerrain.getClass());
            }
        }
    }
}
