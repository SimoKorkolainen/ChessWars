/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor.map;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.pieces.Piece;

/**
 * PieceSelectionButton on nappi, jota käytetään editorissa pelinappulan tyypin valitsemiseen.
 */
public class PieceSelectionButton extends JToggleButton {
    private Piece piece;
    
    public PieceSelectionButton(Piece piece) {
        this.piece = piece;
        
        super.setIcon(new ImageIcon(piece.getImage()));

    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }
    
    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
    
    public void setColor(ArmyColor color) {
        piece.setColor(color);
        super.setIcon(new ImageIcon(piece.getImage()));
        super.repaint();
    }

    public Piece getPiece() {
        return piece;
    }
    
}
