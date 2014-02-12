package com.ouchadam.fyp.analysis;

import helper.MidiHelper;
import helper.TestWithMocks;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class NoteProcessorTest extends TestWithMocks {

    private MidiTrack midiTrack;
    private NoteProcessor noteProcessor;

    @Override
    protected void before() {
        midiTrack = MidiHelper.readPokerFace();
        noteProcessor = new NoteProcessor();
    }

    @Test
    public void should_not_be_the_same_size_as_the_original_list_containing_note_off() throws Exception {
        List<ContainedMidiNote> midiNotes = noteProcessor.process(midiTrack.getNotes());

        assertThat(midiNotes.size()).isNotEqualTo(midiTrack.getNotes().size());
    }

    @Test
    public void should_not_contain_any_note_off_messages() throws Exception {
        List<ContainedMidiNote> midiNotes = noteProcessor.process(midiTrack.getNotes());
        for (ContainedMidiNote midiNote : midiNotes) {
            assertThat(midiNote.getType()).isNotEqualTo(MidiNote.Type.OFF);
        }
    }

}
