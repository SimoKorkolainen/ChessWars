package symmetricgroup.chesswars.players;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author simokork
 */
public enum ArmyColor {
    BLACK("Black"), WHITE("White"), GREEN("Green"), BLUE("Blue"), RED("Red"), YELLOW("Yellow");
    
    private String color;
    
    private ArmyColor(String color) {
        this.color = color;
    }
    
    @Override
    public String toString() {
        return color;
    }
}
