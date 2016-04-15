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
import symmetricgroup.chesswars.pieces.Pawn;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.terrain.Mountains;
import symmetricgroup.chesswars.terrain.Terrain;
import symmetricgroup.chesswars.terrain.Woods;

/**
 *
 * @author Simo
 */
public class MapParserTest {
    private BattleMap map;
    private BattleMap result;
    public MapParserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        map = new BattleMap(12, 11);
        map.setPiece(5, 8, new Pawn(ArmyColor.WHITE));
        map.setPiece(11, 9, new Rook(ArmyColor.RED));
        map.setPiece(4, 4, new Pawn(ArmyColor.GREEN));
        map.setPiece(7, 5, new Rook(ArmyColor.RED));
        map.setPiece(5, 5, new Pawn(ArmyColor.GREEN));
        map.setPiece(4, 6, new Rook(ArmyColor.BLUE));
        map.setTerrain(4, 10, new Mountains());
        map.setTerrain(5, 10, new Woods());
        map.setTerrain(6, 1, new Mountains());
        map.setTerrain(5, 0, new Woods());
        map.setTerrain(0, 10, new Mountains());
        map.setTerrain(5, 10, new Woods());
        String mapConf = MapParser.mapToString(map);
        result = MapParser.stringToMap(mapConf);

    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void mapSizeIsPreservedDuringParsing() {
        assertEquals(map.getWidth(), result.getWidth());
        assertEquals(map.getHeight(), result.getHeight());
    
    }
    
    @Test
    public void terrainsArePreservedDuringParsing() {

        
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                Terrain terrain = map.getTerrain(i, j);
                Terrain copyTerrain = result.getTerrain(i, j);
                
                assertEquals(terrain.getClass(), copyTerrain.getClass());
            }
        }
    
    }
    
    @Test
    public void piecesArePreservedDuringParsing() {

        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                Piece piece = map.getPiece(i, j);
                Piece copyPiece = result.getPiece(i, j);
                
                if(piece == null || copyPiece == null) {
                    assertTrue(piece == null && copyPiece == null);
                    break;
                }
                
                assertEquals(piece.getClass(), copyPiece.getClass());
                assertEquals(piece.getColor(), copyPiece.getColor());
            }
        }
    }
    
}
