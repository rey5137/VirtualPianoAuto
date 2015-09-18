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
public class Track {
    
    public int index;
    public String mTag;
    
    public Track(int index){
        this.index = index;
        mTag = "Track " + (index + 1);
    }
    
    @Override
    public String toString(){
        return mTag;
    }
    
}
