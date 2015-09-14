/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualpianoauto.midi;

/**
 *
 * @author Rey
 */
public enum NoteName {
    
    C("C"),
    C_POUND("C#"),
    D("D"),
    D_POUND("D#"),
    E("E"),
    F("F"),
    F_POUND("F#"),
    G("G"),
    G_POUND("G#"),
    A("C#"),
    A_POUND("A#"),
    B("B");
    
    final String name;
    
    NoteName(String name){
        this.name = name;
    }
    
    public static NoteName fromIndex(int index){
        switch(index){
            case 0:
                return NoteName.C;
            case 1:
                return NoteName.C_POUND;
            case 2:
                return NoteName.D;   
            case 3:
                return NoteName.D_POUND;
            case 4:
                return NoteName.E;
            case 5:
                return NoteName.F;   
            case 6:
                return NoteName.F_POUND;
            case 7:
                return NoteName.G;
            case 8:
                return NoteName.G_POUND;   
            case 9:
                return NoteName.A;
            case 10:
                return NoteName.A_POUND;
            case 11:
                return NoteName.B;      
            default:
                return null;
        }
    }

    public static int toIndex(NoteName name){
        switch(name){
            case C:
                return 0;
            case C_POUND:
                return 1;
            case D:
                return 2;    
            case D_POUND:
                return 3;
            case E:
                return 4;
            case F:
                return 5; 
            case F_POUND:
                return 6;
            case G:
                return 7;
            case G_POUND:
                return 8;    
            case A:
                return 9;
            case A_POUND:
                return 10;
            case B:
                return 11;    
            default:
                return -1;
        }
    }
    @Override
    public String toString() {
        return name;
    }
    
}
