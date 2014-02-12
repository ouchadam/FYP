package com.ouchadam.fyp.analysis;

import helper.TestFileOpener;
import helper.TestWithMocks;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class MidiReaderTest extends TestWithMocks {

    private MidiReader midiReader;

    @Override
    protected void before() {
        midiReader = new MidiReader(new MidiMessageMarshaller());
    }

    @Test
    public void read_midi_meta() throws Exception {
        MidiTrack midiTrack = midiReader.read(TestFileOpener.pokerFaceMidi());
        MidiMeta meta = midiTrack.getMeta();

//        printMeta(midiTrack.getMeta());
        assertThat(meta).isNotNull();
    }

    private void printMeta(MidiMeta meta) {
        System.out.println("Track count : " + meta.getTrackCount());
        System.out.println("Division type : " + meta.getDivision());
        System.out.println("Resolution : " + meta.getResolution());
        System.out.println("Tick length : " + meta.getTickLength());
        System.out.println("Track length : " + meta.getEventCount());
        System.out.println("Bars : " + meta.getBarCount());
    }

    @Test
    public void read_note_data() throws Exception {
        MidiTrack midiTrack = midiReader.read(TestFileOpener.pokerFaceMidi());
        List<MidiNote> notes = midiTrack.getNotes();

        assertThat(notes).isNotEmpty();
    }

}
