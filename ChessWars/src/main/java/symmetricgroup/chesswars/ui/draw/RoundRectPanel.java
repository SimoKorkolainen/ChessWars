/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Simo
 */
public class RoundRectPanel extends JPanel {
    private Color outline;
    private Color fill;
    private int border;
    private JPanel inner;

    public RoundRectPanel(Color outline, Color fill, int border, JPanel inner) {
        this.outline = outline;
        this.fill = fill;
        this.border = border;
        this.inner = inner;
        super.add(inner);
    }
    
    
    public void paintComponent(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(hints);
        
        Dimension d = inner.getSize();
        
        int width = (int) Math.round(d.getWidth()) + border * 2;
        int height = (int) Math.round(d.getHeight()) + border * 2;
        
        
        int offset = 2;
        g2d.setColor(fill);
        g2d.fillRoundRect(offset + border, offset + border, width - 2 * offset - 2 * border, height - 2 * offset - 2 * border, height / 3, height / 3);

        g2d.setColor(outline);
        g2d.setStroke(new BasicStroke(3f));
        g2d.drawRoundRect(offset + border, offset + border, width - 2 * offset - 2 * border, height - 2 * offset - 2 * border, height / 3, height / 3);

    }
}
