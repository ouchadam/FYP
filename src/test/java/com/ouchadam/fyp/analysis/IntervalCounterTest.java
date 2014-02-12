package com.ouchadam.fyp.analysis;

import helper.MidiHelper;
import helper.TestWithMocks;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class IntervalCounterTest extends TestWithMocks {

    private IntervalCounter intervalCounter;

    @Override
    protected void before() {
        intervalCounter = new IntervalCounter();
    }

    @Test
    public void should_have_a_max_interval_of_17_for_pokerface() {
        MidiTrack midiTrack = MidiHelper.readPokerFace();
        NoteProcessor noteProcessor = new NoteProcessor();
        List<ContainedMidiNote> midiNotes = noteProcessor.process(midiTrack.getNotes());

        int maxInterval = intervalCounter.max(midiNotes);

        assertThat(maxInterval).isEqualTo(17);
    }

}
