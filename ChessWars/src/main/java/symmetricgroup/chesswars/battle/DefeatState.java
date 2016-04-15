/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.battle;

import java.util.ArrayList;
import java.util.List;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.players.Player;

/**
 * DefeatState on luokka, johon talletetaan pelaajan häviötila.
 * Luokan metodien avulla on mahdollista toteuttaa ja kumota pelaajan häviö.
 * Tekoälyn toiminnan kannalta on välttämätöntä, että pelaajan häviö voidaan kumota.
 */
public class DefeatState {
    private Player player;
    private int playerPos;
    private List<Move> removeMove;

    public DefeatState(Player player, int playerPos) {
        this.player = player;
        this.playerPos = playerPos;
        this.removeMove = new ArrayList<Move>();
    }
    
    
    
    
    public void removeDefeated(BattleMap map) {
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {

                Piece piece = map.getPiece(i, j);
                if (piece == null) {
                    continue;
                }
                
                if (piece.getColor() == player.getColor()) {
                    
                    Move move = new Move(i, j, i, j, null, piece);
                    map.doMove(move);
                    removeMove.add(move);
                
                }

            }
        }
    
    }
    
    public void putDefeatedBack(BattleMap map) {
        for (Move i : removeMove) {
                  
            map.undoMove(i);
        
        }
    
    }
    
    

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPlayerPos() {
        return playerPos;
    }

    public void setPlayerPos(int playerPos) {
        this.playerPos = playerPos;
    }

    public List<Move> getDefeatedRemoveMoves() {
        return removeMove;
    }
    
    
}
