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
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JToggleButton;

/**
 *
 * @author simokork
 */
public class RoundRectImageButton extends JToggleButton {


    private int border;
    BufferedImage image;

    public RoundRectImageButton(int border, BufferedImage image) {
        super();
        super.setBorderPainted(false);
        super.setOpaque(true);
        super.setContentAreaFilled(false);
        super.setFocusPainted(false);
        super.setMargin(new Insets(0, 0, 0, 0));
        this.image = image;
        this.border = border;
    }
    
    public Color lighten(Color color, int amount) {
        int r = Math.min(color.getRed() + amount, 255);
        int g = Math.min(color.getGreen() + amount, 255);
        int b = Math.min(color.getBlue() + amount, 255);
        return new Color(r, g, b);
    }
    
    public BufferedImage icon(Color borderColor, Color fill) {
        int width = image.getWidth() + 2 * border;
        int height = image.getHeight() + 2 * border;
        
        BufferedImage icon = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        
        Graphics2D g2d = (Graphics2D) icon.createGraphics();
        
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(hints);

        
        
        int offset = 2;
        g2d.setColor(fill);
        g2d.fillRoundRect(offset, offset, width - 2 * offset, height - 2 * offset, height / 3, height / 3);

        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(3f));
        g2d.drawRoundRect(offset, offset, width - 2 * offset, height - 2 * offset, height / 3, height / 3);
        
        g2d.drawImage(image, border, border, null);

        g2d.dispose();
        return icon;
    } 
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
