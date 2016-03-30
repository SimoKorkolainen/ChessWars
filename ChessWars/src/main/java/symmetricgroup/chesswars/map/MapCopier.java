/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.map;

import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.terrain.Terrain;
import symmetricgroup.chesswars.terrain.Woods;

/**
 *
 * @author Simo
 */
public class MapCopier {
    public static BattleMap copy(BattleMap map) {
        BattleMap copy = new BattleMap(map.getWidth(), map.getHeight());
        copy.setMap(copyPieces(map));
        copy.setMapTerrain(copyTerrains(map));
        return copy;
    }
    public static Terrain[][] copyTerrains(BattleMap map) {
        Terrain[][] copy = new Terrain[map.getHeight()][map.getWidth()];
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                copy[j][i] = map.getTerrain(i, j).copy();
            }
        }
        return copy;
    }
    public static Piece[][] copyPieces(BattleMap map) {
        Piece[][] copy = new Piece[map.getHeight()][map.getWidth()];
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getPiece(i, j) == null) {
                    copy[j][i] = null;
                } else {
                    copy[j][i] = map.getPiece(i, j).copy();
                }
            }
        }
        return copy;
    }
    
}
