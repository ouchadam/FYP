package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.Log;
import com.ouchadam.fyp.analysis.Division;
import com.ouchadam.fyp.analysis.MidiMeta;
import com.ouchadam.fyp.analysis.midi.Sequenced16thMidiNote;

import javax.sound.midi.*;
import java.util.List;

public class MidiPlayer {

    private static final int SIXTEENTH = (960 * 4) / 16;
    private final MidiSystemWrapper midiSystem;

    private Sequencer sequencer;
    private Playback playback;

    public MidiPlayer(MidiSystemWrapper midiSystem) {
        this.midiSystem = midiSystem;
    }

    public void play(MidiMeta meta, List<Sequenced16thMidiNote> notes) {
        play(notes, meta.getDivision(), meta.getResolution());
    }

    public void play(List<Sequenced16thMidiNote> notes, Division division, int ticksPerQuarter) {
        try {
            Sequence sequence = getSequence(notes, division, ticksPerQuarter);
            playSequence(sequence);
        } catch (Exception e) {
            Log.e("Failed to play sequence", e);
        }
    }

    private void playSequence(Sequence sequence) throws MidiUnavailableException, InvalidMidiDataException {
        sequencer = midiSystem.getSequencer();
        sequencer.setTempoInBPM(60);
        sequencer.open();
        sequencer.setSequence(sequence);
        sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        sequencer.start();

        new Thread(runnable).start();

    }

    public void setPlayback(Playback playback) {
        this.playback = playback;
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int prevSixteenth = -1;
            do {
                try {
                    int sixteenth = Math.round((float) sequencer.getTickPosition() / SIXTEENTH) % 16;
                    if (sixteenth != prevSixteenth) {
                        prevSixteenth = sixteenth;
                        internalPlayback.onNextSixteenth(sixteenth);
                    }
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (sequencer.isRunning());
        }
    };

    private final Playback internalPlayback = new Playback() {
        @Override
        public void onNextSixteenth(int position) {
            if (playback != null) {
                playback.onNextSixteenth(position);
            }
        }
    };

    private Sequence getSequence(List<Sequenced16thMidiNote> notes, Division division, int ticksPerQuarter) throws InvalidMidiDataException {
        Sequence sequence = new Sequence(division.value(), ticksPerQuarter, 1);

        Track track = sequence.createTrack();
        track.add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, 20, 0), 0));
        for (Sequenced16thMidiNote midiNote : notes) {
            Log.i("Adding : " + midiNote.getNote() + "(" + midiNote.getKey() + ")" + " at " + midiNote.getTick() + " : " + midiNote.getType() + " to : " + midiNote.getNoteOff().getTick());
            track.add(midiNote.getNoteOn());
            track.add(midiNote.getNoteOff());
        }
        return sequence;
    }

    public void stop() {
        if (isPlaying()) {
            sequencer.stop();
        }
    }

    public boolean isPlaying() {
        return sequencer != null && sequencer.isRunning();
    }

}
