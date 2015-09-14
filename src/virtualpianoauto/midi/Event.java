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
public class Event {
    
    public final boolean down;
    public final Key key;
    public final long tick;
    public final int velocity;
    
    Event(boolean down, Key key, long tick, int velocity){
        this.down = down;
        this.key = key;
        this.tick = tick;
        this.velocity = velocity;
    }
    
    public static Event instance(boolean down, Key key, long tick, int velocity){
        return new Event(down, key, tick, velocity);
    }

    @Override
    public String toString() {
        return "Event{" + "down=" + down + ", key=" + key + ", tick=" + tick + ", velocity=" + velocity + '}';
    }
    
}
