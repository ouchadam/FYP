package com.ouchadam.fyp.analysis;

import com.ouchadam.fyp.analysis.midi.MidiNote;
import com.ouchadam.fyp.presentation.MidiSelection;
import com.ouchadam.fyp.presentation.MidiSystemWrapper;

import javax.sound.midi.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MidiReader {

    private static final int FIRST_TRACK_INDEX = 0;
    private final MidiMessageMarshaller midiMessageMarshaller;
    private final MidiSystemWrapper midiSystem;

    public MidiReader(MidiMessageMarshaller midiMessageMarshaller, MidiSystemWrapper midiSystem) {
        this.midiMessageMarshaller = midiMessageMarshaller;
        this.midiSystem = midiSystem;
    }

    public MidiTrack read(MidiSelection midiSelection) throws IOException, InvalidMidiDataException {
        return read(new FileInputStream(midiSelection.getMidiFile()));
    }

    public MidiTrack read(File file) throws IOException, InvalidMidiDataException {
        return read(new FileInputStream(file));
    }

    public MidiTrack read(InputStream input) throws InvalidMidiDataException, IOException {
        return read(midiSystem.getSequence(input));
    }

    private MidiTrack read(Sequence sequence) {
        Track firstTrack = getFirstTrack(sequence);
        MidiMeta meta = createMeta(sequence, firstTrack);
        List<MidiNote> notes = processSequence(firstTrack);
        return new MidiTrack(meta, notes);
    }

    private MidiMeta createMeta(Sequence sequence, Track track) {
        return MidiMeta.from(sequence, track);
    }

    private Track getFirstTrack(Sequence sequence) {
        if (sequence.getTracks().length > 1) {
            System.out.println("Sequence has more than 1 track, only the first track will be used");
        }
        return sequence.getTracks()[FIRST_TRACK_INDEX];
    }

    private List<MidiNote> processSequence(Track track) {
        List<MidiNote> notes = new ArrayList<MidiNote>(track.size());
        for (int index = 0; index < track.size(); index++) {
            MidiEvent midiEvent = track.get(index);
            long tick = midiEvent.getTick();
            MidiMessage message = midiEvent.getMessage();
            if (message instanceof ShortMessage) {
                ShortMessage shortMessage = (ShortMessage) message;
                if (midiMessageMarshaller.isNoteMessage(shortMessage)) {
                    notes.add(midiMessageMarshaller.toNote(shortMessage, tick));
                }
            }
        }
        return notes;
    }

}
