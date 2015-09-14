/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualpianoauto;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import virtualpianoauto.midi.Event;
import virtualpianoauto.midi.Key;

/**
 *
 * @author Rey
 */
public class SongPlayer {
    
    private static final String TAG = SongPlayer.class.getSimpleName();
            
    private Thread mThread;
    
    public void play(Event[] events, long milisPerTick) throws AWTException{
        stop();
        mThread = new Thread(new Task(events, milisPerTick));
        mThread.start();
    }
    
    public void stop(){
        if(mThread != null){
            mThread.interrupt();
            mThread = null;
        }
    }
    
    public boolean isPlaying(){
        return mThread != null;
    }
    
    private class Task implements Runnable{
        Event[] mEvents;   
        long mMilisPerTick;
        Robot mRobot;
        
        public Task(Event[] events, long milisPerTick) throws AWTException{
            mEvents = events;
            mMilisPerTick = milisPerTick;
            mRobot = new Robot();
        }

        private void pressKey(Key key){
            if(key.shiftDown)
                mRobot.keyPress(KeyEvent.VK_SHIFT);
            mRobot.keyPress(key.keyEvent);
            
            System.out.println("press key: " + key);
        }
        
        private void releaseKey(Key key){
            if(key.shiftDown)
                mRobot.keyRelease(KeyEvent.VK_SHIFT);
            mRobot.keyRelease(key.keyEvent);
        }
        
        private boolean sleep(long tick){
            try {
                Thread.sleep(tick * mMilisPerTick);
                return true;
            } catch (InterruptedException ex) {
                return false;
            }
        }
        
        @Override
        public void run() {            
            if(mEvents[0].tick > 0 && !sleep(mEvents[0].tick)){
                mThread = null;
                return;
            }
                        
            for(int i = 0; i < mEvents.length; i++){
                if(mEvents[i].down)
                    pressKey(mEvents[i].key);
                else
                    releaseKey(mEvents[i].key);                
                
                if(Thread.interrupted()){
                    mThread = null;
                    return;
                }
                
                if(i < mEvents.length - 1){
                    if(!sleep(mEvents[i + 1].tick - mEvents[i].tick)){
                        mThread = null;
                        return;
                    }
                }
            }
            
            mThread = null;
        }        
    }
}
