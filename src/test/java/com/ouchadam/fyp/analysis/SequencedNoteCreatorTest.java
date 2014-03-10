package com.ouchadam.fyp.analysis;

import com.ouchadam.fyp.analysis.midi.*;
import helper.TestWithMocks;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class SequencedNoteCreatorTest extends TestWithMocks {

    static final int TICKS_PER_QUARTER = 960;
    private SequencedNoteCreator sequencedNoteCreator;

    @Override
    protected void before() {
        sequencedNoteCreator = new SequencedNoteCreator(TICKS_PER_QUARTER);
    }

    @Test
    public void create_16th_notes_from_contained_notes() throws Exception {
        List<ContainedMidiNote> notes = createNotes(10);

        List<Sequenced16thMidiNote> sequenced16thMidiNotes = sequencedNoteCreator.process(notes);

        assertThat(sequenced16thMidiNotes).hasSize(10);
    }

    private List<ContainedMidiNote> createNotes(int size) {
        List<ContainedMidiNote> containedMidiNotes = new ArrayList<ContainedMidiNote>(size);
        for (int index = 0; index < size; index++) {
            long tick = index * TICKS_PER_QUARTER;
            containedMidiNotes.add(createContainedNote(tick, tick + 100));
        }
        return containedMidiNotes;
    }

    private ContainedMidiNote createContainedNote(long tickOn, long tickOff) {
        MidiNote on = BaseMidiNote.from(Type.ON, 0x60, 0x60, tickOn);
        MidiNote off = BaseMidiNote.from(Type.OFF, 0x60, 0x60, tickOff);
        return ContainedMidiNote.from(on, off);
    }
}
