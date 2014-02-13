package com.ouchadam.fyp.analysis;

import java.util.ArrayList;
import java.util.List;

public class ContainedNoteCreator implements Processor<List<ContainedMidiNote>, List<MidiNote>> {

    @Override
    public List<ContainedMidiNote> process(List<MidiNote> notes) {
        List<ContainedMidiNote> containedMidiNotes = new ArrayList<ContainedMidiNote>();
        for (MidiNote currentNote : notes) {
            if (currentNote.getType() == MidiNote.Type.ON) {
                containedMidiNotes.add(handleNote(notes, currentNote));
            }
        }
        return containedMidiNotes;
    }

    private ContainedMidiNote handleNote(List<MidiNote> notes, MidiNote noteOn) {
        MidiNote noteOff = getNoteOffFor(noteOn, notes);
        return ContainedMidiNote.from(noteOn, noteOff);
    }

    private MidiNote getNoteOffFor(MidiNote midiNote, List<MidiNote> notes) {
        int startingIndex = notes.indexOf(midiNote);
        for (int index = startingIndex; index < notes.size(); index++) {
            MidiNote currentNote = notes.get(index);
            if (isNoteOffOfCurrentNote(midiNote, currentNote)) {
                return currentNote;
            }
        }
        throw new RuntimeException("There is no note off for the note : " + midiNote);
    }

    private boolean isNoteOffOfCurrentNote(MidiNote midiNote, MidiNote currentNote) {
        return isNoteOff(currentNote) && isSameNote(midiNote, currentNote);
    }

    private boolean isSameNote(MidiNote midiNote, MidiNote currentNote) {
        return currentNote.getKey() == midiNote.getKey();
    }

    private boolean isNoteOff(MidiNote currentNote) {
        return currentNote.getType() == MidiNote.Type.OFF;
    }
}
