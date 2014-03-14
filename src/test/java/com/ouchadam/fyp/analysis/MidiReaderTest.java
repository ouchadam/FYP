package com.ouchadam.fyp.analysis;

import com.ouchadam.fyp.analysis.midi.MidiNote;
import com.ouchadam.fyp.presentation.midi.MidiSystemWrapper;
import helper.TestFileOpener;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Spy;

import java.io.InputStream;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MidiReaderTest extends TestWithMocks {

    private MidiReader midiReader;
    @Spy MidiSystemWrapper midiSystemWrapper;

    @Override
    protected void before() {
        midiReader = new MidiReader(new MidiMessageMarshaller(), midiSystemWrapper);
    }

    @Test
    public void read_midi_meta() throws Exception {
        MidiTrack midiTrack = midiReader.read(TestFileOpener.pokerFaceMidi());
        MidiMeta meta = midiTrack.getMeta();

//        printMeta(midiTrack.getMeta());
        assertThat(meta).isNotNull();
    }

    @Test
    public void use_the_midi_system_to_read() throws Exception {
        midiReader.read(TestFileOpener.pokerFaceMidi());

        verify(midiSystemWrapper).getSequence(any(InputStream.class));
    }

    @Test
    public void read_note_data() throws Exception {
        MidiTrack midiTrack = midiReader.read(TestFileOpener.pokerFaceMidi());
        List<MidiNote> notes = midiTrack.getNotes();

        assertThat(notes).isNotEmpty();
    }

    private void printMeta(MidiMeta meta) {
        System.out.println("Track count : " + meta.getTrackCount());
        System.out.println("Division type : " + meta.getDivision());
        System.out.println("Resolution : " + meta.getResolution());
        System.out.println("Tick length : " + meta.getTickLength());
        System.out.println("Track length : " + meta.getEventCount());
        System.out.println("Bars : " + meta.getBarCount());
    }

}
