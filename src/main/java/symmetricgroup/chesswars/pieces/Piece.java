/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package symmetricgroup.chesswars.pieces;

import symmetricgroup.chesswars.map.ArmyColor;
import symmetricgroup.chesswars.map.BattleMap;
import symmetricgroup.chesswars.map.Move;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simokork
 */
public abstract class Piece {
    private int x;
    private int y;
    private ArmyColor color;
    
    
    private int moveLength;
    private int moveDirX[];
    private int moveDirY[];
    private boolean eatDir[];
    private boolean mustEatDir[];

    public Piece(int x, int y, ArmyColor color) {
        this.x = x;
        this.y = y;
        this.color = color;
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

    
    
    
    public List<Move> getMoves(BattleMap map) {
        List<Move> moves = new ArrayList<>();
        
        
        for (int i = 0; i < moveDirX.length; i++) {
            for (int j = 1; j <= moveLength; j++) {
                
                int newX = x + j * moveDirX[j];
                int newY = y + j * moveDirY[j];
                
                if (!map.isInside(newX, newY)) {
                    break;
                }
                Piece eaten = map.getPiece(newX, newY);
                if (eaten != null) {
                    if (eatDir[i] && map.getTeam(eaten.getColor()) != map.getTeam(color)) {
                        
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
    
    
    

}
