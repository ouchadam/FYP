package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.Member;

import javax.sound.midi.Sequence;
import java.io.File;

class MemberToMidiSaver {

    private final MidiFileChooser midiFileChooser;
    private final MidiSystemWrapper midiSystem;
    private final MemberToSequence memberToSequence;

    private Sequence sequence;

    MemberToMidiSaver(MidiFileChooser midiFileChooser, MidiSystemWrapper midiSystem, MemberToSequence memberToSequence) {
        this.midiFileChooser = midiFileChooser;
        this.midiSystem = midiSystem;
        this.memberToSequence = memberToSequence;
    }

    public void save(final Member member) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                sequence = memberToSequence.from(member);
                midiFileChooser.choose(onSaveFileChosen);
            }
        }).start();
    }


    private final MidiFileChooser.FileChooserResult onSaveFileChosen = new MidiFileChooser.FileChooserResult() {
        @Override
        public void onSelection(File file) {
            midiSystem.write(sequence, file);
        }

        @Override
        public void onCancel() {
            // do nothing
        }
    };

}
