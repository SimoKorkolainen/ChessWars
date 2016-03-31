/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.players;

import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.battle.Move;
import symmetricgroup.chesswars.players.UserPlayer;
import symmetricgroup.chesswars.pieces.Piece;

/**
 *
 * @author Simo
 */
public class UserControl {
    private UserPlayer player;
    private SelectedPiece selected;
    private Battle battle;

    public UserControl(Battle battle) {
        this.player = player;
        this.battle = battle;
    }
    
    
    
    public void handleLeftMouseReleased(int x, int y) {
        
        if (selected == null) {

            selectPiece(x, y);
            
        } else {

            checkForNextMove(x, y);
            selected = null;
        }
    }
    
    private void selectPiece(int x, int y) {
        BattleMap map = battle.getMap();
        
        Piece piece = map.getPiece(x, y);

        if (map.isInside(x, y) && piece != null) {
            
            selected = new SelectedPiece(piece, x, y);
        }
    }
    
    private void checkForNextMove(int x, int y) {

        Piece piece = selected.getPiece();
        
        if (piece.getColor() == battle.nextColorToMove()) {
            
            Move move = findNextMoveEndingAt(x, y);
            
            if (move != null) {
                
                player.setNextMove(move);
                
            } else {
                
                selectPiece(x, y);
                
            }
        }
    }
    
    private Move findNextMoveEndingAt(int x, int y) {

        Piece piece = selected.getPiece();

        Move move = null;
        for (Move i : piece.getMoves(selected.getX(), selected.getY(), battle)) {

            if (i.getEndX() == x && i.getEndY() == y) {
                move = i;
                break;
            }

        }

        return move;
    }

    public SelectedPiece getSelected() {
        return selected;
    }

    public void setPlayer(UserPlayer player) {
        this.player = player;
    }
    

}
