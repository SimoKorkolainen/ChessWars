package symmetricgroup.chesswars.players;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 * ArmyColor on sotilaan värin kuvaamiseen käyttetty Enum.
 */
public enum ArmyColor {
    BLACK("Black", new Color(30, 30, 30)), WHITE("White", new Color(220, 220, 220)), GREEN("Green", new Color(195, 255, 104)), BLUE("Blue", new Color(148, 199, 182)), RED("Red", new Color(255, 107, 107)), YELLOW("Yellow", new Color(252, 208, 54));
    
    private String color;
    private Color drawingColor;

    /**
     * Konstrukstori luo värin.
     * @param color värin nimi
     * @param drawingColor värin piirtämisessä käytetty väri
     */
    private ArmyColor(String color, Color drawingColor) {
        this.color = color;
        this.drawingColor = drawingColor;
        
    }

    public Color getDrawingColor() {
        return drawingColor;
    }
    

    
    @Override
    public String toString() {
        return color;
    }
}
