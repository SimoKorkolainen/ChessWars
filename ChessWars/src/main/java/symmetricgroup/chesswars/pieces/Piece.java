/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.pieces;

import java.awt.Graphics2D;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.battle.Move;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.terrain.Mountains;
import symmetricgroup.chesswars.terrain.Terrain;

/**
 *
 * @author simokork
 */
public abstract class Piece {
    private ArmyColor color;
    
    private String name;

    
    private int moveLength;
    private int moveDirX[];
    private int moveDirY[];
    private boolean eatDir[];
    private boolean mustEatDir[];

    public Piece(ArmyColor color, String name) {
        this.color = color;
        this.name = name;
    }

    public void setMoveLength(int moveLength) {
        this.moveLength = moveLength;
    }

    public void setMoveDirX(int[] moveDirX) {
        this.moveDirX = moveDirX;
    }

    public void setMoveDirY(int[] moveDirY) {
        this.moveDirY = moveDirY;
    }

    public void setEatDir(boolean[] eatDir) {
        this.eatDir = eatDir;
    }

    
    
    
    public List<Move> getMoves(int x, int y, Battle battle) {
        List<Move> moves = new ArrayList<>();
        BattleMap map = battle.getMap();
        
        for (int i = 0; i < moveDirX.length; i++) {
            int stepsLeft = moveLength;
            for (int j = 1; j <= moveLength; j++) {
                
                int newX = x + j * moveDirX[i];
                int newY = y + j * moveDirY[i];
                
                if (!map.isInside(newX, newY)) {
                    break;
                
                }
                
                Terrain terrain = map.getTerrain(newX, newY);
                
                if (terrain.getClass() == Mountains.class) {
                    if (!getName().equals("Pawn")) {
                        break;
                    }
                }
                
                stepsLeft -= terrain.moveCost();
                
                if (stepsLeft < 0 && j > 1) {
                    break;
                }
                
                Piece eaten = map.getPiece(newX, newY);
                if (eaten != null) {
                    if (eatDir[i] && battle.getTeam(eaten.getColor()) != battle.getTeam(color)) {
                        
                        boolean eat = !lastEatenWasOwnPawn(battle) || lastEaterWasPawn(battle) || !isPawn(); 
                        
                        if (eat) {
                            moves.add(new Move(x, y, newX, newY, this, eaten));
                        }
                        
                    }
                    
                    break;
                }
                if (!mustEatDir[i]) {
                    Move move = new Move(x, y, newX, newY, this, null);
                    moves.add(move);
                    

                }
                
            }
        
        }
        
        return moves;
    }
    
    public void pawnSpecialEating(List<Move> moves, int x, int y, Battle battle) {


    }
    
    private boolean isPawn() {
        return "Pawn".equals(getName());
    }

    private boolean lastEaterWasPawn(Battle battle) {
        if (battle.getMoves().isEmpty()) {
            return false;
        }
        
        Move last = battle.getLastMove();
        Piece eater = last.getPiece();
        return eater.isPawn();
    }

    private boolean lastEatenWasOwnPawn(Battle battle) {
        if (battle.getMoves().isEmpty()) {
            return false;
        }
        
        Move last = battle.getLastMove();
        Piece eaten = last.getEaten();
        if (eaten == null) {
            return false;
        }
        
        if (!eaten.isPawn()) {
            return false;
        }
        
        return eaten.getColor() == getColor();
        
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
    
    public abstract BufferedImage getImage();    
    
    
    public void draw(Graphics2D g2d, int x, int y) {
        g2d.drawImage(getImage(), x, y, null);
    }

    public void setColor(ArmyColor color) {
        this.color = color;
    }
    
    @Override
    public String toString() {
        return color.toString() + "_" + name;
    }
    
    public abstract Piece copy();
}
