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
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * RoundRectTextButton on pyöreäreunainen nappi, jossa on tekstiä.
 */
public class RoundRectTextButton extends JButton {
    private String buttonText;
    private int width;
    private int minWidth;
    private int height;
    private Color color;
    private Color textColor;
    public RoundRectTextButton(int minWidth, int height, Color color, Color textColor, String buttonText) {
        super();
        super.setBorderPainted(false);
        super.setOpaque(true);
        super.setContentAreaFilled(false);
        super.setFocusPainted(false);
        super.setMargin(new Insets(0, 0, 0, 0));
        this.height = height;
        this.color = color;
        this.textColor = textColor;
        this.minWidth = minWidth;
        setButtonText(buttonText);
    }
    
    private Color lighten(Color color, int amount) {
        int r = Math.min(color.getRed() + amount, 255);
        int g = Math.min(color.getGreen() + amount, 255);
        int b = Math.min(color.getBlue() + amount, 255);
        return new Color(r, g, b);
    }
    
    private BufferedImage icon(Color borderColor, Color fill) {

        
        BufferedImage icon = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        
        Graphics2D g2d = (Graphics2D) icon.createGraphics();
        
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(hints);
        
        Font font = new Font("SansSerif", Font.PLAIN, height / 2);
        
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();

        
        int x = width / 2 - metrics.stringWidth(buttonText) / 2;
        int y = height / 2 - metrics.getHeight() / 2 + metrics.getAscent();        
        
        int offset = 2;
        g2d.setColor(fill);
        g2d.fillRoundRect(offset, offset, width - 2 * offset, height - 2 * offset, height / 3, height / 3);

        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(3f));
        g2d.drawRoundRect(offset, offset, width - 2 * offset, height - 2 * offset, height / 3, height / 3);
        
        g2d.setColor(textColor);
        g2d.drawString(buttonText, x, y);
        g2d.dispose();
        return icon;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
        calculateWidth();
        super.setPressedIcon(new ImageIcon(icon(lighten(color, 30), lighten(color, 60))));
        super.setIcon(new ImageIcon(icon(color, lighten(color, 30))));
    }
    public void calculateWidth() {
        BufferedImage icon = new BufferedImage(minWidth, height, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        
        Graphics2D g2d = (Graphics2D) icon.createGraphics();
        Font font = new Font("SansSerif", Font.PLAIN, height / 2);
        
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();
        int gap = 10;
        width = Math.max(minWidth, metrics.stringWidth(buttonText) + gap);
    }
}
