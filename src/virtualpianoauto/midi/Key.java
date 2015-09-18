/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualpianoauto.midi;

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 *
 * @author Rey
 */
public enum Key {
    
    KEY_1('1', NoteName.C, 1, KeyEvent.VK_1, false),
    KEY_1_POUND('!', NoteName.C_POUND, 1, KeyEvent.VK_1, true),
    KEY_2('2', NoteName.D, 1, KeyEvent.VK_2, false),
    KEY_2_POUND('@', NoteName.D_POUND, 1, KeyEvent.VK_2, true),
    KEY_3('3', NoteName.E, 1, KeyEvent.VK_3, false),
    KEY_4('4', NoteName.F, 1, KeyEvent.VK_4, false),
    KEY_4_POUND('$', NoteName.F_POUND, 1, KeyEvent.VK_4, true),
    KEY_5('5', NoteName.G, 1, KeyEvent.VK_5, false),
    KEY_5_POUND('%', NoteName.G_POUND, 1, KeyEvent.VK_5, true),
    KEY_6('6', NoteName.A, 1, KeyEvent.VK_6, false),
    KEY_6_POUND('^', NoteName.A_POUND, 1, KeyEvent.VK_6, true),
    KEY_7('7', NoteName.B, 1, KeyEvent.VK_7, false),
    
    KEY_8('8', NoteName.C, 2, KeyEvent.VK_8, false),
    KEY_8_POUND('*', NoteName.C_POUND, 2, KeyEvent.VK_8, true),
    KEY_9('9', NoteName.D, 2, KeyEvent.VK_9, false),
    KEY_9_POUND('(', NoteName.D_POUND, 2, KeyEvent.VK_9, true),
    KEY_0('0', NoteName.E, 2, KeyEvent.VK_0, false),
    KEY_Q('q', NoteName.F, 2, KeyEvent.VK_Q, false),
    KEY_Q_POUND('Q', NoteName.F_POUND, 2, KeyEvent.VK_Q, true),
    KEY_W('w', NoteName.G, 2, KeyEvent.VK_W, false),
    KEY_W_POUND('W', NoteName.G_POUND, 2, KeyEvent.VK_W, true),
    KEY_E('e', NoteName.A, 2, KeyEvent.VK_E, false),
    KEY_E_POUND('E', NoteName.A_POUND, 2, KeyEvent.VK_E, true),
    KEY_R('r', NoteName.B, 2, KeyEvent.VK_R, false),
    
    KEY_T('t', NoteName.C, 3, KeyEvent.VK_T, false),
    KEY_T_POUND('T', NoteName.C_POUND, 3, KeyEvent.VK_T, true),
    KEY_Y('y', NoteName.D, 3, KeyEvent.VK_Y, false),
    KEY_Y_POUND('Y', NoteName.D_POUND, 3, KeyEvent.VK_Y, true),
    KEY_U('u', NoteName.E, 3, KeyEvent.VK_U, false),
    KEY_I('i', NoteName.F, 3, KeyEvent.VK_I, false),
    KEY_I_POUND('I', NoteName.F_POUND, 3, KeyEvent.VK_I, true),
    KEY_O('o', NoteName.G, 3, KeyEvent.VK_O, false),
    KEY_O_POUND('O', NoteName.G_POUND, 3, KeyEvent.VK_O, true),
    KEY_P('p', NoteName.A, 3, KeyEvent.VK_P, false),
    KEY_P_POUND('P', NoteName.A_POUND, 3, KeyEvent.VK_P, true),
    KEY_A('a', NoteName.B, 3, KeyEvent.VK_A, false),
    
    KEY_S('s', NoteName.C, 4, KeyEvent.VK_S, false),
    KEY_S_POUND('S', NoteName.C_POUND, 4, KeyEvent.VK_S, true),
    KEY_D('d', NoteName.D, 4, KeyEvent.VK_D, false),
    KEY_D_POUND('D', NoteName.D_POUND, 4, KeyEvent.VK_D, true),
    KEY_F('f', NoteName.E, 4, KeyEvent.VK_F, false),
    KEY_G('g', NoteName.F, 4, KeyEvent.VK_G, false),
    KEY_G_POUND('G', NoteName.F_POUND, 4, KeyEvent.VK_G, true),
    KEY_H('h', NoteName.G, 4, KeyEvent.VK_H, false),
    KEY_H_POUND('H', NoteName.G_POUND, 4, KeyEvent.VK_H, true),
    KEY_J('j', NoteName.A, 4, KeyEvent.VK_J, false),
    KEY_J_POUND('J', NoteName.A_POUND, 4, KeyEvent.VK_J, true),
    KEY_K('k', NoteName.B, 4, KeyEvent.VK_K, false),
    
    KEY_L('l', NoteName.C, 5, KeyEvent.VK_L, false),
    KEY_L_POUND('L', NoteName.C_POUND, 5, KeyEvent.VK_L, true),
    KEY_Z('z', NoteName.D, 5, KeyEvent.VK_Z, false),
    KEY_Z_POUND('Z', NoteName.D_POUND, 5, KeyEvent.VK_Z, true),
    KEY_X('x', NoteName.E, 5, KeyEvent.VK_X, false),
    KEY_C('c', NoteName.F, 5, KeyEvent.VK_C, false),
    KEY_C_POUND('C', NoteName.F_POUND, 5, KeyEvent.VK_C, true),
    KEY_V('v', NoteName.G, 5, KeyEvent.VK_V, false),
    KEY_V_POUND('V', NoteName.G_POUND, 5, KeyEvent.VK_V, true),
    KEY_B('b', NoteName.A, 5, KeyEvent.VK_B, false),
    KEY_B_POUND('B', NoteName.A_POUND, 5, KeyEvent.VK_B, true),
    KEY_N('n', NoteName.B, 5, KeyEvent.VK_N, false),
    
    KEY_M('M', NoteName.C, 6, KeyEvent.VK_M, false),
    ;
    
    public final char character;
    final int key;
    final NoteName name;
    final int octave;
    public final int keyEvent;
    public final boolean shiftDown;
    
    static volatile HashMap<Integer, Key> KEYS_MAP;
        
    Key(char character, NoteName name, int octave, int keyEvent, boolean shiftDown){
        this.character = character;
        this.name = name;
        this.octave = octave;
        this.key = (octave + 1) * 12 + NoteName.toIndex(name);  
        this.keyEvent = keyEvent;
        this.shiftDown = shiftDown;
        
        init();
    }
    
    private void init(){
        if(KEYS_MAP == null){
            synchronized(Key.class){
                if(KEYS_MAP == null)
                    KEYS_MAP = new HashMap<>();
            }
        }
        
        KEYS_MAP.put(this.key, this);
    }
    
    public static Key fromValue(int value){
        Key key = KEYS_MAP.get(value);
        if(key == null){
            int octave = value / 12 - 1;
            int note = value % 2;
            
            System.err.println("Can not find key for value = " + value + " octave = " + octave + " note = " + NoteName.fromIndex(note));
            
        }
        return key;
    }

    @Override
    public String toString() {
        return "Key{" + "char=" + character + ", key=" + key + ", note=" + name + octave + '}';
    }    
    
    
}
