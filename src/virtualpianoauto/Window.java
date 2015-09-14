/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualpianoauto;

import com.sun.jna.platform.win32.WinDef;

/**
 *
 * @author Rey
 */
public class Window {
    public WinDef.HWND hWnd;
    public String title;
    
    public Window(WinDef.HWND hWnd, String title){
        this.hWnd = hWnd;
        this.title = title;
    }
}
