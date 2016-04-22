/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.pieces.PieceParser;
import symmetricgroup.chesswars.terrain.Terrain;
import symmetricgroup.chesswars.terrain.TerrainParser;

/**
 * MapParser on luokka, jonka avulla BattleMap-kartta voidaan muuttaa String-muotoon ja toisinpäin.
 */

public class MapParser {

    
    /**
     * Metodi muuttaa kartan tekstimuotoon.
     * @param map tekstiksi muutettava kartta
     * @return kartan tekstinä
     */
    public static String mapToString(BattleMap map) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(map.getWidth());
        builder.append(" ");
        builder.append(map.getHeight());
        builder.append(" ");
        
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                Piece piece = map.getPiece(j, i);
                
                if (piece != null) {
                    builder.append(piece.toString());
                } else {
                    builder.append("null");
                }
                builder.append(" ");
                
                Terrain terrain = map.getTerrain(j, i);
                
                
                builder.append(terrain.getName());
                builder.append(" ");
            }
        }
        
        
        return builder.toString();
    }
    /**
     * Metodi luo kartan tekstin perusteella.
     * @param mapConf kartan tiedot tekstinä
     * @return tekstin perusteella luodun kartan tai null,
     * jos kartan luonti ei onnistunut.
     */
    public static BattleMap stringToMap(String mapConf) {
 
        
        try {
            String conf[] = mapConf.split(" ");
            
            int width = Integer.parseInt(conf[0]);
            int height = Integer.parseInt(conf[1]);
            
            BattleMap map = new BattleMap(width, height);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int ind = 2 + j * 2 + i * width * 2;
                    String pieceConf = conf[ind];
                    
                    Piece piece = null;
                   
                        
                    if (!"null".equals(pieceConf)) {
                        piece = PieceParser.stringToPiece(pieceConf);
                    }
                    Terrain terrain = TerrainParser.stringToTerrain(conf[ind + 1]);
                    
                    map.setPiece(j, i, piece);
                    map.setTerrain(j, i, terrain);
                    
                }
            }
            
            return map;
            
        } catch (Exception e) {
            System.out.println("Unable to parse map: " + e.getMessage());
        }
        return null;
    }
}

