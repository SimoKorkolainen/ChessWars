/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.battle.move;

import java.util.ArrayList;
import java.util.List;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.players.ArmyColor;

/**
 *
 * @author Simo
 */
public class BattleMoveCalculator {
    private Battle battle;
    private BattleMap map;
    
    /**
     * Konstruktori luo taistelun siirtojen laskemiseen tarkoitetun olion
     * @param battle taistelu, josta halutaan laskea siirtoja
     */
    public BattleMoveCalculator(Battle battle) {
        this.battle = battle;
        this.map = battle.getMap();
    }
    
   
    /**
     * Metodi palauttaa seuraavaksi vuorossa olevan pejaajan
     * kaikki mahdolliset siirrot.
     * @return kaikki mahdolliset siirrot
     */
    public List<Move> allPossibleNextMoves() {
        List<Move> all = new ArrayList<>();
        
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                
                addMovesInPosition(all, i, j); 
                
            }
        
        }
    
        return all;
    }
    
    private void addMovesInPosition(List<Move> moves, int i, int j) {
        
        Piece piece = map.getPiece(i, j);

        ArmyColor next = battle.nextColorToMove();

        if (piece != null && piece.getColor() == next) {

            moves.addAll(piece.getMoves(i, j, battle));

        }
        
    }

}
