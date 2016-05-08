package symmetricgroup.chesswars.pieces;

import java.util.ArrayList;
import java.util.List;
import symmetricgroup.chesswars.battle.Battle;
import symmetricgroup.chesswars.battle.move.Move;
import symmetricgroup.chesswars.terrain.Mountains;
import symmetricgroup.chesswars.terrain.Terrain;

/**
 * PieceMoveCalculator laskee nappula siirrot.
 * @author Simo
 */
public class PieceMoveCalculator {
        
    
    /**
     * Metodi palauttaa siirrot, jotka nappulan on mahdollista tehdä taistelun kartalla.
     * Pelaajien vuoroilla ei ole vaikutusta metodin toimintaan.
     * @param piece nappula, jonka siirrot halutaan laskea
     * @param x nappulan x-koordinaatti
     * @param y nappulan y-koordinaatti
     * @param battle taistelu, jossa siirrot halutaan laskea
     * @return kaikki mahdolliset siirrot
     */
    public static List<Move> getMoves(Piece piece, int x, int y, Battle battle) {
        
        int moveDirX[] = piece.getMoveDirX();

        List<Move> moves = new ArrayList<>();

        
        for (int i = 0; i < moveDirX.length; i++) {

            addMovesInDir(moves, piece, battle, x, y, i);
        }
        
        return moves;
    }
    
    private static void addMovesInDir(List<Move> moves, Piece piece, Battle battle, int x, int y, int dir) {
        int moveLength = piece.getMoveLength();
        int stepsLeft = moveLength;
        for (int j = 1; j <= moveLength; j++) {

            stepsLeft = addMove(moves, piece, battle, stepsLeft, x, y, j, dir);

            if (stepsLeft <= 0) {
                break;
            }
        }
    }
    
    private static int addMove(List<Move> moves, Piece piece, Battle battle, int stepsLeft, int x, int y, int len, int dir) {

        Move move = createMove(x, y, dir, len, piece);

        if (!moveIsPossible(move, battle)) {
            return 0;
        }
        
        stepsLeft = stepsLeft(battle, stepsLeft, move);
        
        return handleMove(moves, battle, stepsLeft, len, dir, move);
    }
    
    private static int handleMove(List<Move> moves, Battle battle, int stepsLeft, int len, int dir, Move move) {

        if (stepsLeft < 0 && len > 1) {
            return 0;
        }
        
        if (canEat(dir, battle, move, moves)) {
            return 0;
        }

        handleFreeMove(dir, move, moves);
        
        return stepsLeft;
    }
    
    private static boolean moveIsPossible(Move move, Battle battle) {
    
        if (!moveEndIsInsideMap(move, battle)) {
            return false;
        }
        
        return !blockedByMountains(battle, move);

    }
    
    private static Move createMove(int x, int y, int dir, int len, Piece piece) {
        int moveDirX[] = piece.getMoveDirX();
        int moveDirY[] = piece.getMoveDirY();
        
        int newX = x + len * moveDirX[dir];
        int newY = y + len * moveDirY[dir];
        
        return new Move(x, y, newX, newY, piece, null);
    }
    
    private static boolean moveEndIsInsideMap(Move move, Battle battle) {
        return battle.getMap().isInside(move.getEndX(), move.getEndY());
        
    }
    
    private static int stepsLeft(Battle battle, int stepsLeft, Move move) {
        Terrain terrain = battle.getMap().getTerrain(move.getEndX(), move.getEndY());
        stepsLeft -= terrain.moveCost();

        return stepsLeft;
    }
    
    private static boolean blockedByMountains(Battle battle, Move move) {
        Terrain terrain = battle.getMap().getTerrain(move.getEndX(), move.getEndY());

        if (terrain.getClass() == Mountains.class) {
            return !isPawn(move.getPiece());
        }
        
        return false;
    }
    
    private static boolean canEat(int dir, Battle battle, Move move, List<Move> moves) {
        Piece eaten = battle.getMap().getPiece(move.getEndX(), move.getEndY());
        if (eaten != null) {
            move.setEaten(eaten);
            handleEating(dir, battle, move, moves);
            return true;
        }
        return false;
    }
    
    private static void handleEating(int dir, Battle battle, Move move, List<Move> moves) {
        Piece piece = move.getPiece();
        Piece eaten = move.getEaten();

        if (piece.getEatDir()[dir] && battle.getTeam(eaten.getColor()) != battle.getTeam(piece.getColor())) {

            boolean eat = !lastEatenWasOwnPawn(piece, battle) || lastEaterWasPawn(battle) || !isPawn(piece); 

            if (eat) {
                moves.add(move);
            }
        }
    }
    
    private static void handleFreeMove(int dir, Move move, List<Move> moves) {
        Piece piece = move.getPiece();
        boolean mustEatDir[] = piece.getMustEatDir();
        if (!mustEatDir[dir]) {
            moves.add(move);
        }
    }
    
    private static boolean isPawn(Piece piece) {
        return "Pawn".equals(piece.getName());
    }
    private static boolean lastEaterWasPawn(Battle battle) {
        if (battle.getMoves().isEmpty()) {
            return false;
        }
        
        Move last = battle.getLastMove();
        Piece eater = last.getPiece();
        return isPawn(eater);
    }

    private static boolean lastEatenWasOwnPawn(Piece piece, Battle battle) {

        Piece eaten = lastEaten(battle);
        if (eaten == null) {
            return false;
        }
        
        if (!isPawn(eaten)) {
            return false;
        }
        
        return eaten.getColor() == piece.getColor();
        
    }
    
    private static Piece lastEaten(Battle battle) {
        if (battle.getMoves().isEmpty()) {
            return null;
        }
        
        Move last = battle.getLastMove();
        return last.getEaten();
    }
    
}
