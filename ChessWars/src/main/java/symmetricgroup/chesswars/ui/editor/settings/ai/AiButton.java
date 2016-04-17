/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symmetricgroup.chesswars.ui.editor.settings;

import java.awt.Color;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import symmetricgroup.chesswars.players.ArmyColor;
import symmetricgroup.chesswars.ui.editor.RoundRectTextButton;

/**
 * AiButton on nappi, jonka avulla voidaan valita editorissa ohjaako tekoäly väriä.
 */
public class AiButton extends RoundRectTextButton {
    private ArmyColor color;
    private boolean aiIsOn;
    public AiButton(ArmyColor color) {
        super(30, 30, color.getDrawingColor(), new Color(30, 30, 30), "");
        this.color = color;
        this.aiIsOn = true;
        super.setMargin(new Insets(1, 1, 1, 1));
        updateText();
    }

    public boolean isAiIsOn() {
        return aiIsOn;
    }

    public void setAiIsOn(boolean aiIsOn) {
        this.aiIsOn = aiIsOn;
    }
    public void updateText() {
        if (aiIsOn) {
            super.setButtonText("on");
            
        } else {
            super.setButtonText("off");
        }
    }
}
