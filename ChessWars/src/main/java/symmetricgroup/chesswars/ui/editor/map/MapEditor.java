/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor.map;

import java.util.Collection;
import java.util.List;
import symmetricgroup.chesswars.battle.defeat.BattleWinnerChecker;
import symmetricgroup.chesswars.ui.editor.map.MapEditorPanel;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.pieces.Bishop;
import symmetricgroup.chesswars.pieces.King;
import symmetricgroup.chesswars.pieces.Knight;
import symmetricgroup.chesswars.pieces.Pawn;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.pieces.Queen;
import symmetricgroup.chesswars.pieces.Rook;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.terrain.Mountains;
import symmetricgroup.chesswars.terrain.Plain;
import symmetricgroup.chesswars.terrain.Terrain;
import symmetricgroup.chesswars.terrain.Woods;
import symmetricgroup.chesswars.ui.editor.EditorPanel;
import symmetricgroup.chesswars.ui.editor.settings.AiButton;
import symmetricgroup.chesswars.ui.editor.settings.ai.AiButtonPanel;
import symmetricgroup.chesswars.ui.editor.settings.teams.TeamButton;
import symmetricgroup.chesswars.ui.editor.settings.teams.TeamSelectionPanel;


/**
 * MapEditor on kartan editoimiseen käytetty luokka.
 */
public class MapEditor {
    private MapEditorPanel panel;
    private BattleMap map;
    private EditorPanel editor;

    public MapEditor(MapEditorPanel panel, EditorPanel editor, BattleMap map) {
        this.panel = panel;
        this.map = map;
        this.editor = editor;
    }


    public void addSelectedToMap(int x, int y) {

        System.out.println("editor: x = " + x + " y = " + y);
        Piece piece = panel.getSelectedPiece();
        Terrain terrain = panel.getSelectedTerrain();
        
        
        
        if (map.isInside(x, y)) {
            
            Piece old = map.getPiece(x, y);
            
            if (piece != null) {

                
                if (piece.getName().equals("Pawn")) {
                    map.setPiece(x, y, new Pawn(piece.getColor()));
                }
                Terrain mapTerrain = map.getTerrain(x, y);
                if (!mapTerrain.getName().equals("Mountains")) {
                    map.setPiece(x, y, piece.copy());
                }
                
                updateTeamAndAiButton(piece.getColor());
            }
            
            if (terrain != null) {

                map.setPiece(x, y, null);

                map.setTerrain(x, y, terrain.copy());

                
            }
            
            
            if (old != null) {
                updateTeamAndAiButton(old.getColor());
            }
        }
        editor.getSave().updateSaveButton();
    }
    
    public void updateTeamAndAiButton(ArmyColor color) {
        TeamSelectionPanel teamPanel = editor.getTeamPanel();
        AiButtonPanel aiPanel = editor.getAiPanel();
        
        if (BattleWinnerChecker.mapContainsKing(map, color)) {
            
            teamPanel.getButton(color).setPlayerIn();
            aiPanel.getButton(color).setPlayerIn();
        } else {
        
            teamPanel.getButton(color).setPlayerOut();
            aiPanel.getButton(color).setPlayerOut();
        }
    
    }
    

}
