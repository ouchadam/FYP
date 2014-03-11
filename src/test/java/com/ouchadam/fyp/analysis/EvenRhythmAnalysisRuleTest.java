package com.ouchadam.fyp.analysis;

import com.ouchadam.fyp.analysis.midi.ContainedMidiNote;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import helper.TestWithMocks;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EvenRhythmAnalysisRuleTest extends TestWithMocks {

    private EvenRhythmAnalysisRule evenRhythmAnalysisRule;

    @Override
    protected void before() {
        evenRhythmAnalysisRule = new EvenRhythmAnalysisRule();
    }

    @Test
    public void completely_even_should_have_zero_odd_percent() throws Exception {
        List<ContainedMidiNote> evenNotes = createNotes(0, 2, 4, 6);

        int percentOfOddNotes = evenRhythmAnalysisRule.percentOfOddNotes(evenNotes);

        assertThat(percentOfOddNotes).isZero();
    }

    @Test
    public void fifty_percent_even_should_have_50_odd_percent()  {
        List<ContainedMidiNote> evenNotes = createNotes(0, 2, 5, 7);

        int percentOfOddNotes = evenRhythmAnalysisRule.percentOfOddNotes(evenNotes);

        assertThat(percentOfOddNotes).isEqualTo(50);
    }

    @Test
    public void completely_odd_should_have_100_odd_percent() throws Exception {
        List<ContainedMidiNote> evenNotes = createNotes(1, 3, 5, 7);

        int percentOfOddNotes = evenRhythmAnalysisRule.percentOfOddNotes(evenNotes);

        assertThat(percentOfOddNotes).isEqualTo(100);
    }

    @Test
    public void edge_case() throws Exception {
        List<ContainedMidiNote> evenNotes = createNotes(0, 2, 5, 8, 10, 12, 14);

        int percentOfOddNotes = evenRhythmAnalysisRule.percentOfOddNotes(evenNotes);

        assertThat(percentOfOddNotes).isEqualTo(14);
    }

    private List<ContainedMidiNote> createNotes(int... positions) {
        List<ContainedMidiNote> midiNotes = new ArrayList<ContainedMidiNote>(positions.length);
        for (int position : positions) {
            midiNotes.add(mockNote(position));
        }
        return midiNotes;
    }

    private ContainedMidiNote mockNote(int position) {
        ContainedMidiNote midiNote = mock(ContainedMidiNote.class);
        when(midiNote.getTick()).thenReturn(createTickForPosition(position));
        return midiNote;
    }

    private long createTickForPosition(int position) {
        return (960 * 4) / 16 * position;
    }
}
