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
    
    public interface OnPlayListener{
        
        void onPlayed(int count, int total);
        
        void onInterrupted();
        
        void onEnded();
        
    }
    
    public void play(Event[] events, long milisPerTick, OnPlayListener listener) throws AWTException{
        stop();
        mThread = new Thread(new Task(events, milisPerTick, listener));
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
        OnPlayListener mListener;
        
        public Task(Event[] events, long milisPerTick, OnPlayListener listener) throws AWTException{
            mEvents = events;
            mMilisPerTick = milisPerTick;
            mRobot = new Robot();
            mListener = listener;
        }

        private void pressKey(Key key){
            if(key.shiftDown)
                mRobot.keyPress(KeyEvent.VK_SHIFT);
            mRobot.keyPress(key.keyEvent);
            
            mRobot.keyRelease(KeyEvent.VK_SHIFT);
            mRobot.keyRelease(key.keyEvent);
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
                if(mListener != null)
                    mListener.onInterrupted();
                return;
            }
                        
            for(int i = 0; i < mEvents.length; i++){
                if(mEvents[i].down)
                    pressKey(mEvents[i].key);
                else
                    releaseKey(mEvents[i].key);
                
                if(mListener != null)
                    mListener.onPlayed(i + 1, mEvents.length);
                
                if(Thread.interrupted()){
                    mThread = null;
                    if(mListener != null)
                        mListener.onInterrupted();
                    return;
                }
                
                if(i < mEvents.length - 1){
                    if(!sleep(mEvents[i + 1].tick - mEvents[i].tick)){
                        mThread = null;
                        if(mListener != null)
                            mListener.onInterrupted();
                        return;
                    }
                }
            }
            
            mThread = null;
            if(mListener != null)
                mListener.onEnded();
        }        
    }
}
