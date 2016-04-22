/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.pieces;

import java.awt.Graphics2D;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.battle.move.Move;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.terrain.Mountains;
import symmetricgroup.chesswars.terrain.Terrain;

/**
 * Piece on nappuloiden toimintaa abstrahoiva luokka.
 */
public abstract class Piece {
    private ArmyColor color;
    
    private String name;

    
    private int moveLength;
    private int moveDirX[];
    private int moveDirY[];
    private boolean eatDir[];
    private boolean mustEatDir[];
   /**
     * Konstruktori luo nappulan.
     * @param color nappulan väri
     * @param name nappulan tyypin nimi
     */
    public Piece(ArmyColor color, String name) {
        this.color = color;
        this.name = name;
    }

    protected void setMoveLength(int moveLength) {
        this.moveLength = moveLength;
    }

    protected void setMoveDirX(int[] moveDirX) {
        this.moveDirX = moveDirX;
    }

    protected void setMoveDirY(int[] moveDirY) {
        this.moveDirY = moveDirY;
    }

    protected void setEatDir(boolean[] eatDir) {
        this.eatDir = eatDir;
    }


    public ArmyColor getColor() {
        return color;
    }

    public int getMoveLength() {
        return moveLength;
    }

    public void setMustEatDir(boolean[] mustEatDir) {
        this.mustEatDir = mustEatDir;
    }

    public String getName() {
        return name;
    }
    
    /**
     *
     * @return palautaa nappulan kuvan
     */
    public abstract BufferedImage getImage();    
    


    public void setColor(ArmyColor color) {
        this.color = color;
    }
    
    @Override
    public String toString() {
        return color.toString() + "_" + name;
    }
    
    /**
     * 
     * @return palauttaa kopion nappulasta 
     */
    public abstract Piece copy();

    public int[] getMoveDirX() {
        return moveDirX;
    }

    public int[] getMoveDirY() {
        return moveDirY;
    }

    public boolean[] getEatDir() {
        return eatDir;
    }

    public boolean[] getMustEatDir() {
        return mustEatDir;
    }
    /**
     * Metodi palauttaa siirrot, jotka nappulan on mahdollista tehdä taistelun kartalla.
     * Pelaajien vuoroilla ei ole vaikutusta metodin toimintaan.
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @param battle taistelu, jossa siirrot halutaan laskea
     * @return kaikki mahdolliset siirrot
     */
    public List<Move> getMoves(int x, int y, Battle battle) {
        
        return PieceMoveCalculator.getMoves(this, x, y, battle);
        
    }
}
