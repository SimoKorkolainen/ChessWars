/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.terrain;

import symmetricgroup.chesswars.pieces.Bishop;
import symmetricgroup.chesswars.pieces.King;
import symmetricgroup.chesswars.pieces.Knight;
import symmetricgroup.chesswars.pieces.Pawn;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.pieces.Queen;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.players.ArmyColor;

/**
 * TerrainParser on luokka, jonka avulla Terrain-olio on mahdollista luoda tekstin perusteella.
 */
public class TerrainParser {
    public static Terrain stringToTerrain(String terrainName) {
    
        
        switch (terrainName) {
        
            case "Plain": return new Plain();
            case "Mountains": return new Mountains();
            case "Woods": return new Woods();

            default: return null;
        }
    
    }
}
