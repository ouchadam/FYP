package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.Division;
import com.ouchadam.fyp.analysis.MidiMeta;
import com.ouchadam.fyp.analysis.midi.Sequenced16thMidiNote;

import javax.sound.midi.*;
import java.util.List;

public class MidiPlayer {

    public void play(MidiMeta meta, List<Sequenced16thMidiNote> notes) {
        play(notes, meta.getDivision(), meta.getResolution());
    }

    public void play(List<Sequenced16thMidiNote> notes, Division division, int ticksPerQuarter) {
        try {
            Sequence sequence = new Sequence(division.value(), ticksPerQuarter, 1);

            Track track = sequence.createTrack();
            track.add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, 20, 0), 0));
            for (Sequenced16thMidiNote midiNote : notes) {
                System.out.println("Adding : " + midiNote.getNote() + "(" + midiNote.getKey() + ")" + " at " + midiNote.getTick() + " : " + midiNote.getType() + " to : " + midiNote.getNoteOff().getTick());
                track.add(midiNote.getNoteOn());
                track.add(midiNote.getNoteOff());
            }
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.setTempoInBPM(60);
            sequencer.open();
            sequencer.setSequence(sequence);
            sequencer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
