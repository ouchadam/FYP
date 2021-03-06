package com.ouchadam.fyp.analysis;

import com.ouchadam.fyp.analysis.midi.ContainedMidiNote;

import java.util.List;

import org.junit.Test;

import helper.MidiHelper;
import helper.TestWithMocks;

import static org.fest.assertions.api.Assertions.assertThat;

public class MaxIntervalCounterTest extends TestWithMocks {

    private MaxIntervalCounter maxIntervalCounter;

    @Override
    protected void before() {
        maxIntervalCounter = new MaxIntervalCounter();
    }

    @Test
    public void should_have_a_max_interval_of_17_for_pokerface() {
        MidiTrack midiTrack = MidiHelper.readPokerFace();
        ContainedNoteCreator containedNoteCreator = new ContainedNoteCreator();
        List<ContainedMidiNote> midiNotes = containedNoteCreator.process(midiTrack.getNotes());

        int maxInterval = maxIntervalCounter.max(midiNotes);

        assertThat(maxInterval).isEqualTo(17);
    }

}
