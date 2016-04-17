/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.GlyphVector;
import symmetricgroup.chesswars.ui.editor.RoundRectTextButton;

/**
 *
 * @author Simo
 */
public class TextDrawer {
    private Color fill;
    private Color outline;
    private int textSize;
    public TextDrawer(Color fill, int textSize) {
        this.fill = fill;
        this.outline = outline;
        this.textSize = textSize;
        
    }
    
    
    public TextDrawer(Color fill, Color outline, int textSize) {
        this.fill = fill;
        this.outline = outline;
        this.textSize = textSize;
        
    }
    
    public void draw(String text, int x, int y, Graphics2D g2d) {
        
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(hints);
        
        Font font = new Font("SansSerif", Font.PLAIN, textSize);
        
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();


        int cornerX = x - metrics.stringWidth(text) / 2;
        int cornerY = y - metrics.getHeight() / 2 + metrics.getAscent();    
        

        GlyphVector gv = font.createGlyphVector(g2d.getFontRenderContext(), text);
       


        if (outline != null) {
            g2d.setStroke(new BasicStroke(4f));
            g2d.setColor(outline);
            g2d.draw(gv.getOutline(cornerX, cornerY));
        }
        
        g2d.setColor(fill);
        g2d.fill(gv.getOutline(cornerX, cornerY));
    }
}
