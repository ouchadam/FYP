package com.ouchadam.fyp.presentation.midi;

import com.ouchadam.fyp.algorithm.Member;

import javax.sound.midi.Sequence;
import java.io.File;

public class MemberToMidiSaver {

    private final MidiFileChooser midiFileChooser;
    private final MidiSystemWrapper midiSystem;
    private final MemberToSequence memberToSequence;

    private Sequence sequence;

    public MemberToMidiSaver(MidiFileChooser midiFileChooser, MidiSystemWrapper midiSystem, MemberToSequence memberToSequence) {
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

    public File asFile(Member member) {
        File midiFile = new File(createMidiFileName());
        midiSystem.write(memberToSequence.from(member), midiFile);
        return midiFile;
    }

    private String createMidiFileName() {
        return "FYP_" + String.valueOf(System.currentTimeMillis());
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
