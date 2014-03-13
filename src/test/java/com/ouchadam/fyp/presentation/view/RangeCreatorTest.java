package com.ouchadam.fyp.presentation.view;

import com.ouchadam.fyp.analysis.midi.Sequenced16thMidiNote;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import helper.TestWithMocks;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RangeCreatorTest extends TestWithMocks {

    private static final int HIGHEST = 100;
    private static final int LOWEST = 10;

    private RangeCreator rangeCreator;

    @Override
    protected void before() {
        rangeCreator = new RangeCreator();
    }

    @Test
    public void testName() {
        List<Sequenced16thMidiNote> midiNotes = new ArrayList<Sequenced16thMidiNote>();
        midiNotes.add(createMidiNote(HIGHEST));
        midiNotes.add(createMidiNote(LOWEST));

        Range range = rangeCreator.from(midiNotes);

        assertThat(range.getHighest()).isEqualTo(HIGHEST);
        assertThat(range.getLowest()).isEqualTo(LOWEST);
    }

    private Sequenced16thMidiNote createMidiNote(int noteValue) {
        Sequenced16thMidiNote midiNote = mock(Sequenced16thMidiNote.class);
        when(midiNote.getKey()).thenReturn(noteValue);
        return midiNote;
    }

}
