/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor;

import java.awt.event.MouseEvent;
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
 *
 * @author Simo
 */
public class MapEditor {
    private EditorPanel panel;
    private BattleMap map;

    public MapEditor(EditorPanel panel, BattleMap map) {
        this.panel = panel;
        this.map = map;
    }


    public void handleLeftMouseReleased(int x, int y) {

        System.out.println("editor: x = " + x + " y = " + y);
        PieceSelectionButton pieceButton = panel.getSelectedPieceButton();
        TerrainSelectionButton terrainButton = panel.getSelectedTerrainButton();
        
        if (map.isInside(x, y)) {
            if (pieceButton != null) {

                Piece piece = pieceButton.getPiece();
                
                
                if (piece.getName().equals("Pawn")) {
                    map.setPiece(x, y, new Pawn(piece.getColor()));
                }
                Terrain terrain = map.getTerrain(x, y);
                if (!terrain.getName().equals("Mountains")) {
                    
                    switch (piece.getName()) {
                        case "Rook": map.setPiece(x, y, new Rook(piece.getColor()));
                                     break;
                        case "Knight": map.setPiece(x, y, new Knight(piece.getColor()));
                                       break;
                        case "Bishop": map.setPiece(x, y, new Bishop(piece.getColor()));
                                     break;
                        case "Queen": map.setPiece(x, y, new Queen(piece.getColor()));
                                      break;
                        case "King": map.setPiece(x, y, new King(piece.getColor()));
                                     break;
                    }
                }

            }
            
            if (terrainButton != null) {
                
                Terrain terrain = terrainButton.getTerrain();
                map.setPiece(x, y, null);
                
                if (terrain.getClass() == Mountains.class) {
                    
                    map.setTerrain(x, y, new Mountains());
                    
                } else if (terrain.getClass() == Woods.class) {
                    
                    map.setTerrain(x, y, new Woods());
                
                } else {
                    
                    map.setTerrain(x, y, new Plain());
                    
                }
                
            }
        }
        
    }
}
