/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * SaveButton on editorissa luodun taistelun tallentamiseen käytetty nappi.
 */
public class SaveButton extends RoundRectTextButton {

    public SaveButton() {
        super(30, 30, new Color(148, 199, 182), new Color(30, 30, 30), "Save");
  
    }

}
