/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor.map;

import symmetricgroup.chesswars.ui.editor.map.MapEditorPanel;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.pieces.Bishop;
import symmetricgroup.chesswars.pieces.King;
import symmetricgroup.chesswars.pieces.Knight;
import symmetricgroup.chesswars.pieces.Pawn;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.pieces.Queen;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.terrain.Mountains;
import symmetricgroup.chesswars.terrain.Plain;
import symmetricgroup.chesswars.terrain.Terrain;
import symmetricgroup.chesswars.terrain.Woods;


/**
 * MapEditor on kartan editoimiseen käytetty luokka.
 */
public class MapEditor {
    private MapEditorPanel panel;
    private BattleMap map;

    public MapEditor(MapEditorPanel panel, BattleMap map) {
        this.panel = panel;
        this.map = map;
        
    }


    public void addSelectedToMap(int x, int y) {

        System.out.println("editor: x = " + x + " y = " + y);
        Piece piece = panel.getSelectedPiece();
        Terrain terrain = panel.getSelectedTerrain();
        
        if (map.isInside(x, y)) {
            if (piece != null) {


                
                
                if (piece.getName().equals("Pawn")) {
                    map.setPiece(x, y, new Pawn(piece.getColor()));
                }
                Terrain mapTerrain = map.getTerrain(x, y);
                if (!mapTerrain.getName().equals("Mountains")) {
                    map.setPiece(x, y, piece.copy());
                }

            }
            
            if (terrain != null) {

                map.setPiece(x, y, null);

                map.setTerrain(x, y, terrain.copy());

                
            }
        }
        
    }
}
