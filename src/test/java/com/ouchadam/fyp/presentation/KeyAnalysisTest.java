package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.Key;
import com.ouchadam.fyp.analysis.MidiMeta;
import com.ouchadam.fyp.analysis.MidiNote;
import com.ouchadam.fyp.analysis.MidiTrack;
import helper.TestWithMocks;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KeyAnalysisTest extends TestWithMocks {

    static final Key[] CMAJOR = new Key[] {Key.C, Key.D, Key.E, Key.F, Key.G, Key.A, Key.B};
    static final Key[] CMINOR_MELODIC = new Key[] {Key.C, Key.D, Key.Eb, Key.F, Key.G, Key.A, Key.B};
    static final Key[] CMINOR_NATURAL = new Key[] {Key.C, Key.D, Key.Eb, Key.F, Key.G, Key.Ab, Key.Bb};

    static final Key[] PROBLEM_SEQUENCE = new Key[] {Key.B, Key.Bb, Key.Gb, Key.B, Key.Bb, Key.Gb, Key.Gb, Key.E, Key.E, Key.E, Key.Eb, Key.E};

    private KeyAnalysis keyAnalysis;

    @Override
    protected void before() {
        keyAnalysis = new KeyAnalysis(new ScaleCreator());
    }

    @Test
    public void c_major() {
        MidiTrack midiTrack = createMidi(CMAJOR);

        KeyAnalysis.Result result = keyAnalysis.analyse(midiTrack.getNotes());

        assertKeyIs(result, Key.C, ScaleCreator.Type.MAJOR);
    }

    @Test
    public void c_minor_melodic() {
        MidiTrack midiTrack = createMidi(CMINOR_MELODIC);

        KeyAnalysis.Result result = keyAnalysis.analyse(midiTrack.getNotes());

        assertKeyIs(result, Key.C, ScaleCreator.Type.MINOR_MELODIC);
    }

    @Test
    public void c_natural() {
        MidiTrack midiTrack = createMidi(CMINOR_NATURAL);

        KeyAnalysis.Result result = keyAnalysis.analyse(midiTrack.getNotes());

        assertKeyIs(result, Key.C, ScaleCreator.Type.MINOR_NATURAL);
    }

    @Test
    public void multiple_occurences() {
        MidiTrack midiTrack = createMidi(PROBLEM_SEQUENCE);

        KeyAnalysis.Result result = keyAnalysis.analyse(midiTrack.getNotes());

        assertKeyIs(result, Key.B, ScaleCreator.Type.MAJOR);
    }

    private void assertKeyIs(KeyAnalysis.Result result, Key key, ScaleCreator.Type type) {
        assertThat(result.key).isEqualTo(key);
        assertThat(result.type).isEqualTo(type);
        assertThat(result.percent).isEqualTo(100);
    }

    private MidiTrack createMidi(Key... keys) {
        List<MidiNote> notes = new ArrayList<MidiNote>();
        for (Key key : keys) {
            notes.add(createMidiNote(key));
        }
        return new MidiTrack(mock(MidiMeta.class), notes);
    }

    private MidiNote createMidiNote(Key key) {
        MidiNote midiNote = mock(MidiNote.class);
        when(midiNote.getNote()).thenReturn(key);
        return midiNote;
    }
}
