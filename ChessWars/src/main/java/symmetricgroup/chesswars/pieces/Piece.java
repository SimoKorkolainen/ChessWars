/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.pieces;

import java.awt.Graphics2D;
import symmetricgroup.chesswars.map.ArmyColor;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.map.Move;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
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

    
    
    
    public List<Move> getMoves(int x, int y, BattleMap map) {
        List<Move> moves = new ArrayList<>();
        
        
        for (int i = 0; i < moveDirX.length; i++) {
            int stepsLeft = moveLength;
            for (int j = 1; j <= moveLength; j++) {
                
                int newX = x + j * moveDirX[j];
                int newY = y + j * moveDirY[j];
                
                if (!map.isInside(newX, newY)) {
                    break;
                
                }
                
                Terrain terrain = map.getTerrain(newX, newY);
                
                if (terrain.getClass() == Mountains.class && getClass() != Pawn.class) {
                    break;
                }
                
                stepsLeft -= terrain.moveCost();
                
                if (stepsLeft < 0 && j > 1) {
                    break;
                }
                
                Piece eaten = map.getPiece(newX, newY);
                if (eaten != null) {
                    if (eatDir[i] && map.getTeam(eaten.getColor()) != map.getTeam(color)) {
                        
                        if (!map.getMoves().isEmpty()) {
                            Move last = map.getMoves().get(map.getMoves().size() - 1);
                            
                            if (last.getEaten().getClass() == Pawn.class && getClass() == Pawn.class) {
                                if (last.getEaten().getColor() == color) {
                                    if (last.getPiece().getClass() != Pawn.class) {
                                        break;
                                    }
                                }
                            
                            }
                        
                        }
                        moves.add(new Move(x, y, newX, newY, this, eaten));
                        
                    }
                    
                    break;
                }
                if (!mustEatDir[j]) {
                    moves.add(new Move(x, y, newX, newY, this, null));
                }
                
            }
        
        }
        
        
        return moves;
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
}
