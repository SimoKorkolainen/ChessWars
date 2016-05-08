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
        
        appendSize(builder, map);
        
        appendMap(builder, map);
        
        
        return builder.toString();
    }
    
    private static void appendSize(StringBuilder builder, BattleMap map) {
        builder.append(map.getWidth());
        builder.append(" ");
        builder.append(map.getHeight());
        builder.append(" ");
    }
    
    private static void appendMap(StringBuilder builder, BattleMap map) {
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {

                appendPiece(builder, map, j, i);
                appendTerrain(builder, map, j, i);

            }
        }
    }
    
    private static void appendPiece(StringBuilder builder, BattleMap map, int x, int y) {
        Piece piece = map.getPiece(x, y);

        if (piece != null) {
            builder.append(piece.toString());
        } else {
            builder.append("null");
        }
        builder.append(" ");
    }
    
    private static void appendTerrain(StringBuilder builder, BattleMap map, int x, int y) {
        Terrain terrain = map.getTerrain(x, y);

        builder.append(terrain.getName());
        builder.append(" ");
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
            
            BattleMap map = createMap(0, conf);
            
            parseMap(map, 2, conf);
            
            return map;
            
        } catch (Exception e) { }
        
        
        return null;
    }
    
    private static BattleMap createMap(int ind, String[] conf) {
        int width = Integer.parseInt(conf[ind]);
        int height = Integer.parseInt(conf[ind + 1]);

        return new BattleMap(width, height);
    }
    
    
    private static void parseMap(BattleMap map, int ind, String[] conf) {
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                int newInd = ind + j * 2 + i * map.getWidth() * 2;
                parsePiece(map, j, i, newInd, conf);

                parseTerrain(map, j, i, newInd + 1, conf);

            }
        }
    }
    
    private static void parsePiece(BattleMap map, int x, int y, int ind, String[] conf) {
        String pieceConf = conf[ind];

        Piece piece = null;


        if (!"null".equals(pieceConf)) {
            piece = PieceParser.stringToPiece(pieceConf);
        }

        map.setPiece(x, y, piece);
    }
    
    private static void parseTerrain(BattleMap map, int x, int y, int ind, String[] conf) {
        Terrain terrain = TerrainParser.stringToTerrain(conf[ind]);

        map.setTerrain(x, y, terrain);
    }
}

