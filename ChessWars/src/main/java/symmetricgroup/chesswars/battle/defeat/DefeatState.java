/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.battle.defeat;

import java.util.ArrayList;
import java.util.List;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;

/**
 * DefeatState on luokka, johon talletetaan pelaajan häviötila.
 * Luokan metodien avulla on mahdollista toteuttaa ja kumota pelaajan häviö.
 * Tekoälyn toiminnan kannalta on välttämätöntä, että pelaajan häviö voidaan kumota.
 */
public class DefeatState {
    private Player player;
    private int playerPos;
    private int turn;
    private List<Move> removeMove;
    
   
    
    /**
     * Konstruktori luo lopputila olion.
     * @param player pelaaja, jonka lopputila halutaan luoda
     * @param playerPos pelaajan, indeksi taistelun pelaajalistalla ennen pelaajan häviötä.
     * @param turn siirron numero
     */
    public DefeatState(Player player, int playerPos, int turn) {
        this.player = player;
        this.playerPos = playerPos;
        this.removeMove = new ArrayList<Move>();
        this.turn = turn;
    }
    
    
    
    /**
     * Metodi poistaa hävinneen pelaajan nappulat kartalta ja tallentaa poistossa käytetyt siirrot listaan,
     * jotta ne voitaisiin kumota tarvittaessa.
     * @param map kartta, josta nappulat halutaan poistaa. 
     */
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
    /**
     * Metodi kumoaa häviöön liittyvän nappuloiden poisto-operaation.
     * 
     * @param map kartta, josta poisto-operaatio halutaan kumota
     */
    public void putDefeatedBack(BattleMap map) {
        for (Move i : removeMove) {
                  
            map.undoMove(i);
        
        }
    
    }
    
    

    public Player getPlayer() {
        return player;
    }


    public int getPlayerPos() {
        return playerPos;
    }

    public List<Move> getDefeatedRemoveMoves() {
        return removeMove;
    }

    public void setRemoveMoves(List<Move> removeMove) {
        this.removeMove = removeMove;
    }
    
    public int getTurn() {
        return turn;
    }
    
}
