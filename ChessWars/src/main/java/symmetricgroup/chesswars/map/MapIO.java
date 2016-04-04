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
import symmetricgroup.chesswars.terrain.Terrain;

/**
 *
 * @author Simo
 */
public class MapIO {
    public static List<BattleMap> loadEditorMaps() {
        List<BattleMap> maps = new ArrayList<>();
    
    
        return maps;
    }
    
    public static void saveMap(String filename, BattleMap map) {
        String mapConf = mapToString(map);
        File file = new File(filename);
        
        try (FileWriter writer = new FileWriter(file)) {
            
            writer.append(mapConf);
            
            
        } catch (Exception e) {
        
            System.out.println("Unable to save the map " + filename + " :"  + e.getMessage());
            
        }
    
    }
    public static BattleMap readMap(String filename) {
        File file = new File(filename);
        
        try (FileReader fileReader = new FileReader(file)) {
            
            BufferedReader reader = new BufferedReader(fileReader);
            
            String mapConf = reader.readLine();
            
            return stringToMap(mapConf);
            
        } catch (Exception e) {
        
            System.out.println("Unable to read the map " + filename + " :" + e.getMessage());
            
        }
        return null;
    }
    public static String mapToString(BattleMap map) {
        StringBuilder builder = new StringBuilder();
        
        builder.append(map.getWidth());
        builder.append(" ");
        builder.append(map.getHeight());
        builder.append(" ");
        
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                Piece piece = map.getPiece(j, i);
                Terrain terrain = map.getTerrain(j, i);
                
                builder.append(piece.toString());
                builder.append(" ");
                builder.append(terrain.toString());
                builder.append(" ");
            }
        }
        
        
        return builder.toString();
    }
    
    public static BattleMap stringToMap(String mapConf) {
        int width = 0;
        int height = 0;
        BattleMap map = new BattleMap(width, height);
    
        return map;
    }
}
