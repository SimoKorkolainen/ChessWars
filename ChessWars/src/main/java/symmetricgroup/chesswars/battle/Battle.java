/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.battle;

import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import symmetricgroup.chesswars.battle.defeat.BattleDefeatHandler;
import symmetricgroup.chesswars.battle.defeat.DefeatState;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.ui.game.BattleMoveThread;

/**
* Battle-luokka mallintaa taistelun kulkua kartalla.
*/
public class Battle {
    private String battleName;
    private BattleMap map;
    private Map<ArmyColor, Integer> team;

    private List<Player> players;
    private int turn;
    private List<Move> moves;
    private List<DefeatState> defeatStates;
    /** 
     * Taistelun luomiseen tarkoitettu konstruktori.
     * 
     * @param map kartta, jossa taistelu tapahtuu
     */
    public Battle(BattleMap map) {
        this.map = map;
        this.team = new HashMap<>();

        this.players = new ArrayList<>();
        this.moves = new ArrayList<>();
        this.defeatStates = new ArrayList<>();
        this.turn = 0;
       
        setDefaultTeams();
    }
    /**
     * Taistelun alottava metodi. Pelaajat tulee olla lisättynä ennen taistelun aloitusta.
     * Metodin suorituksen seurauksena ensimmäiseltä vuorossa olevalta pelaajalta kysytään siirto.
     */
    public void start() {
        BattleMoveThread thread = new BattleMoveThread(this);
        thread.start();
    }
    /**
     * Metodi setTeam asettaa värin color joukkuenumeroksi teamNumberin.
     * 
     * @param color väri, jonka joukkue halutaan määritellä
     * @param teamNumber joukkueen numero
     */
    public void setTeam(ArmyColor color, int teamNumber) {
        team.put(color, teamNumber);
    }
    /**
     * Metodi asettaa oletusarvoiset joukkueet.
     * Jokainen pelaaja on oletusarvoisesti eri joukkueessa.
     *
     */
    public void setDefaultTeams() {
        setTeam(ArmyColor.WHITE, 1);
        setTeam(ArmyColor.BLACK, 2);
        setTeam(ArmyColor.BLUE, 3); 
        setTeam(ArmyColor.RED, 4); 
        setTeam(ArmyColor.GREEN, 5);
        setTeam(ArmyColor.YELLOW, 6);
    }
    /**
     * Metodi addPlayer lisää uuden pelaajan peliin. Pelaajalla oletetaan olevan kuningas kartalla.
     * Pelaajat tulee lisätä ennen taistelun aloitusta.
     * 
     * @param player lisättävä pelaaja
     */
    public void addPlayer(Player player) {
        
        players.add(player);
    }
    /**
     * Metodi toteuttaa siirron taistelussa.
     *
     * @param move  Siirto, joka halutaan tehdä
     * 
     * @see symmetricgroup.chesswars.battle.Move
     */
    public void doMove(Move move) {
        turn = (turn + 1) % players.size();
        map.doMove(move);
        moves.add(move);
        
        Piece eaten = move.getEaten();
        if (eaten != null && "King".equals(eaten.getName())) {
        
            BattleDefeatHandler.handleDefeat(this, eaten.getColor());
        }
    }

    private void undoMove(Move move) {
        Piece eaten = move.getEaten();
        if (eaten != null && "King".equals(eaten.getName())) {
        
            BattleDefeatHandler.undoDefeat(this, defeatStates.remove(defeatStates.size() - 1));
        
        }
        
        turn = (turn - 1 + players.size()) % players.size();
        map.undoMove(move);
    }
    /**
     * Metodi kumoaa edellisen siirron taistelussa.
     *
     * @see symmetricgroup.chesswars.battle.Move
     */
    public void undoLastMove() {
        undoMove(moves.remove(moves.size() - 1));
    }
    /**
     * Metodi palauttaa seuraavaksi vuorossa olevan pelaajan värin.
     *
     * @return seuraavan pelaajan väri 
     */
    public ArmyColor nextColorToMove() {
        return players.get(turn).getColor();
    }
    /**
     * Metodi palauttaa seuraavaksi vuorossa olevan pelaajan.
     *
     * @return Seuraava pelaaja 
     */
    public Player nextPlayerToMove() {
        return players.get(turn);
    }
    
    /**
     *  Metodi kertoo mihin joukkueeseen väri kuuluu
     * @param color väri, jonka joukkue halutaan tietää
     * @return palauttaa värin color joukkueen numeron
     */
    public int getTeam(ArmyColor color) {
        return team.get(color);
    }

    public BattleMap getMap() {
        return map;
    }

    public List<Move> getMoves() {
        return moves;
    }
    /**
     * Metodi getLastMove palauttaa viimeksi kartalla tehdyn siirron.
     * @return viimeksi kartalla tehty siirto
     */
    public Move getLastMove() {
        return moves.get(moves.size() - 1);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Map<ArmyColor, Integer> getTeam() {
        return team;
    }

    public void setTeam(Map<ArmyColor, Integer> team) {
        this.team = team;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<DefeatState> getDefeatStates() {
        return defeatStates;
    }
}
