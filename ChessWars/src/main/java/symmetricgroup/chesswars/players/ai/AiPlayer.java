package symmetricgroup.chesswars.players.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.battle.BattleCopier;
import symmetricgroup.chesswars.battle.move.BattleMoveCalculator;
import symmetricgroup.chesswars.battle.defeat.BattleWinnerChecker;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.players.Player;
import symmetricgroup.chesswars.ui.game.BattleMoveThread;

/**
 * AiPlayer on tekoälyn toiminnallisuuden sisältävä luokka.
 */
public class AiPlayer implements Player {
    private BattleMoveCalculator moveCalculator;
    private int searchDepth;
    private ArmyColor color;
    private Set<ArmyColor> myTeam; //Koodia voi muokata siten, että myTeam on tarpeeton
    private Battle battle;
    private Battle copy;
    private boolean random;

    /**
     * Konstruktori luo tekoälypelaajan.
     * @param searchDepth haun syvyys
     * @param color tekoälyn väri
     * @param battle taistelu, jossa tekoäly taistelee
     * @param random totuusarvo, joka kertoo pelaako tekoäly satunnaisesti
     */
    public AiPlayer(int searchDepth, ArmyColor color, Battle battle, boolean random) {
        this.searchDepth = searchDepth;
        this.color = color;
        this.myTeam = new HashSet<>();
        this.myTeam.add(color);
        this.battle = battle;
        this.random = random;
    }
    
    /**
     * Metodi alustaa tekoälyn joukkueen.
     */
    public void setUpTeam() {
        for (Player i : battle.getPlayers()) {
            if (battle.getTeam(color) == battle.getTeam(i.getColor())) {
                myTeam.add(i.getColor());
            }
        }
    }

    @Override
    public ArmyColor getColor() {
        return color;
    }

 

    public Set<ArmyColor> getMyTeam() {
        return myTeam;
    }
    /**
     * Metodi laskee tekoälyn seuraavan siirron ja toteuttaa sen.
     * @param thread metodia kutsunut BattleMoveThread-olio
     */
    @Override
    public void calculateAndExecuteMove(BattleMoveThread thread) { 
        Move next = alphaBeta();
        thread.executeMove(next);  
    }
    /**
     * Metodi laskee tekoälyn seuraavan siirron
     * Tekoäly pelaa aivan kuin kaikki eri tiimeissä olevat vastustajat
     * olisivat liittoutuneet keskenään ja omien tiimiläisten siirrot olisivat
     * tekoälyn itse päätettävissä.
     * @return paras siirto
     */
    public Move alphaBeta() {
        copy = BattleCopier.copy(battle);
        moveCalculator = new BattleMoveCalculator(copy);
        return new AlphaBeta(this).alphaBeta();
    }
    
    @Override
    public Player copy(Battle battle) {
        return new AiPlayer(searchDepth, color, battle, random);
    }

    public int getSearchDepth() {
        return searchDepth;
    }

    public Battle getBattle() {
        return battle;
    }

    public Battle getCopy() {
        return copy;
    }

    public boolean isRandom() {
        return random;
    }

    public BattleMoveCalculator getMoveCalculator() {
        return moveCalculator;
    }
    
    
}
