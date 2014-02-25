package com.ouchadam.fyp.analysis;

import java.util.ArrayList;
import java.util.List;

public class SequencedNoteCreator implements Processor<List<Sequenced16thMidiNote>, List<ContainedMidiNote>> {

    private final int ticksPerQuarter;

    public SequencedNoteCreator(int ticksPerQuarter) {
        this.ticksPerQuarter = ticksPerQuarter;
    }

    @Override
    public List<Sequenced16thMidiNote> process(List<ContainedMidiNote> notes) {
        List<Sequenced16thMidiNote> sequencedNotes = new ArrayList<Sequenced16thMidiNote>(notes.size());
        for (ContainedMidiNote note : notes) {
            sequencedNotes.add(Sequenced16thMidiNote.from(ticksPerQuarter, note));
        }
        return sequencedNotes;
    }

}
