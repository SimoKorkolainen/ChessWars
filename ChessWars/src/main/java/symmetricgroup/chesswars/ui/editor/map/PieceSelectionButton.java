/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor.map;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.pieces.Piece;
import symmetricgroup.chesswars.ui.editor.RoundRectImageButton;

/**
 * PieceSelectionButton on nappi, jota käytetään editorissa pelinappulan tyypin valitsemiseen.
 */
public class PieceSelectionButton extends RoundRectImageButton {
    private Piece piece;
    
    public PieceSelectionButton(Piece piece) {
        super(1, piece.getImage());
        this.piece = piece;
        updateIcons();
    }
    

    
    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
    
    public void setColor(ArmyColor color) {
        piece.setColor(color);
        setImage(piece.getImage());
        updateIcons();
        super.repaint();
    }

    public Piece getPiece() {
        return piece;
    }
    
    public void updateIcons() {
        
        super.setIcon(new ImageIcon(icon(lighten(Color.gray, 20), lighten(Color.gray, 40))));
        super.setPressedIcon(new ImageIcon(icon(lighten(Color.gray, 50), lighten(Color.gray, 70))));
        super.setSelectedIcon(new ImageIcon(icon(lighten(Color.gray, 50), lighten(Color.gray, 70))));
    }
    
}

    

