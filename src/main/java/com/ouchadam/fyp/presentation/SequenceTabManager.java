package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.MidiNote;
import com.ouchadam.fyp.analysis.MidiTrack;
import com.ouchadam.fyp.presentation.view.StepSequenceView;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

public class SequenceTabManager extends TabManager implements SequenceController {

    private StepSequenceView stepSequenceView;
    private MidiTrack midiTrack;
    private JButton play;

    public SequenceTabManager(JTabbedPane tabbedPane) {
        super(tabbedPane);
    }

    @Override
    public JTabbedPane create() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        stepSequenceView = new StepSequenceView();
        stepSequenceView.init();
        panel.add(stepSequenceView);
        play = new JButton("Play");
        play.setEnabled(false);
        panel.add(play);
        setPlayListener();
        return createTabbedPane("Sequence", panel);
    }

    @Override
    public void open(MidiTrack midiTrack) {
        this.midiTrack = midiTrack;
        stepSequenceView.open(midiTrack);
        play.setEnabled(true);
    }

    void setPlayListener() {
        setClickListener(play, onPlay);
    }

    private OnClickListener onPlay = new OnClickListener() {
        @Override
        public void onClick(Component component) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    playSequence();
                }
            }).start();
        }
    };

    private void playSequence() {
        try {
            Sequence sequence = new Sequence(Sequence.PPQ, 960, 1);
            Track track = sequence.createTrack();
            for (MidiNote midiNote : midiTrack.getNotes()) {
                ShortMessage message = new ShortMessage(midiNote.getType().asStatus(), midiNote.getKey(), midiNote.getVelocity());
                track.add(new MidiEvent(message, midiNote.getTick()));
            }
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(sequence);
            sequencer.start();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

}
