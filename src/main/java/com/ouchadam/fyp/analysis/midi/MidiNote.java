package com.ouchadam.fyp.analysis.midi;

import com.ouchadam.fyp.analysis.Key;

import javax.sound.midi.MidiEvent;

public interface MidiNote {

    int getKey();
    Type getType();
    long getTick();
    int getOctave();
    Key getNote();
    int getVelocity();
    MidiEvent toMessage();

}
