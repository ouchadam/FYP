package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.analysis.Division;
import com.ouchadam.fyp.analysis.midi.Sequenced16thMidiNote;

import javax.sound.midi.*;
import java.awt.*;
import java.io.File;
import java.util.List;

class MemberToMidiFile {

    private final MidiFileChooser midiFileChooser;

    public Sequence sequence;

    MemberToMidiFile(MidiFileChooser midiFileChooser) {
        this.midiFileChooser = midiFileChooser;
    }

    public void save(final Member member) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Sequenced16thMidiNote> notes = new MemberToMidi().convert(member);
                try {
                    sequence = createSequenceFrom(notes);
                    midiFileChooser.choose(onSaveFileChosen);
                } catch (Exception e) {
                    sequence = null;
                    throw new RuntimeException("Couldn't create sequence from provided notes", e);
                }
            }
        }).start();
    }

    private Sequence createSequenceFrom(List<Sequenced16thMidiNote> notes) throws Exception {
        Sequence sequence = new Sequence(Division.PPQ.value(), 960, 1);
        Track track = sequence.getTracks()[0];
        track.add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, 20, 0), 0));
        for (Sequenced16thMidiNote midiNote : notes) {
            System.out.println("Adding : " + midiNote.getNote() + "(" + midiNote.getKey() + ")" + " at " + midiNote.getTick() + " : " + midiNote.getType() + " to : " + midiNote.getNoteOff().getTick());
            track.add(midiNote.getNoteOn());
            track.add(midiNote.getNoteOff());
        }
        return sequence;
    }

    private final MidiFileChooser.FileChooserResult onSaveFileChosen = new MidiFileChooser.FileChooserResult() {
        @Override
        public void onSelection(File file) {
            try {
                MidiSystem.write(sequence, MidiSystem.getMidiFileTypes(sequence)[0], file);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to save");
            }
        }

        @Override
        public void onCancel() {
            // do nothing
        }
    };

}
