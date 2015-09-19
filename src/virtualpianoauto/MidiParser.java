/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualpianoauto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import rx.Observable;
import rx.Subscriber;
import virtualpianoauto.midi.Event;
import virtualpianoauto.midi.Key;
import virtualpianoauto.midi.Song;

/**
 *
 * @author Rey
 */
public class MidiParser {
    
    public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;
    public static final int NOTE_OFF_ALL = 0x58;

    public static Song parse(File file, boolean includeOffEvent) throws InvalidMidiDataException, IOException{
        Sequence sequence = MidiSystem.getSequence(file);        
        Song song = new Song();
        song.miliPerTick = sequence.getMicrosecondLength() / sequence.getTickLength() / 1000;
        
        Track[] tracks = sequence.getTracks();
        song.events = new Event[tracks.length][];
                
        for (int i = 0; i < tracks.length; i++) {
            Track track = tracks[i];
            
            List<Event> list = new ArrayList<>(tracks[i].size());
            
            for(int j = 0, size = track.size(); j < size; j++){
                MidiEvent event = track.get(j);
                long tick = event.getTick();
                MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message;
                    int key = sm.getData1();
                    int velocity = sm.getData2();
                    
                    while(key < 24)
                        key += 12;
                    
                    while(key > 84)
                        key -= 12;
                    
                    if(sm.getCommand() == NOTE_OFF_ALL){
                        //TODO: handle it
                    }
                    else if (sm.getCommand() == NOTE_OFF || (sm.getCommand() == NOTE_ON && velocity <= 0)){
                        if(includeOffEvent)
                            list.add(Event.instance(false, Key.fromValue(key), tick, velocity));         
                    }
                    else if (sm.getCommand() == NOTE_ON)                         
                        list.add(Event.instance(true, Key.fromValue(key), tick, velocity));
                }
            }
            
            song.events[i] = list.toArray(new Event[list.size()]);
        }
        
        return song;
    }
    
    
}
