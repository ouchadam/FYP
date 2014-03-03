package com.ouchadam.fyp.analysis;

import java.util.List;

import com.ouchadam.fyp.analysis.midi.ContainedMidiNote;
import com.ouchadam.fyp.analysis.midi.Type;
import org.junit.Test;

import helper.MidiHelper;
import helper.TestWithMocks;

import static org.fest.assertions.api.Assertions.assertThat;

public class ContainedNoteCreatorTest extends TestWithMocks {

    private MidiTrack midiTrack;
    private ContainedNoteCreator containedNoteCreator;

    @Override
    protected void before() {
        midiTrack = MidiHelper.readPokerFace();
        containedNoteCreator = new ContainedNoteCreator();
    }

    @Test
    public void should_not_be_the_same_size_as_the_original_list_containing_note_off() throws Exception {
        List<ContainedMidiNote> midiNotes = containedNoteCreator.process(midiTrack.getNotes());

        assertThat(midiNotes.size()).isNotEqualTo(midiTrack.getNotes().size());
    }

    @Test
    public void should_not_contain_any_note_off_messages() throws Exception {
        List<ContainedMidiNote> midiNotes = containedNoteCreator.process(midiTrack.getNotes());
        for (ContainedMidiNote midiNote : midiNotes) {
            assertThat(midiNote.getType()).isNotEqualTo(Type.OFF);
        }
    }

}
