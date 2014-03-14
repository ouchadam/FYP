package com.ouchadam.fyp.algorithm.evaluate.rule;

import com.ouchadam.fyp.algorithm.domain.Member;
import com.ouchadam.fyp.algorithm.domain.NoteType;
import com.ouchadam.fyp.algorithm.domain.NoteValue;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;
import com.ouchadam.fyp.presentation.midi.NoteOnFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class EvenRhythmRuleTest {

    private EvenRhythmRule evenRhythmRule;

    @Before
    public void setUp() throws Exception {
        evenRhythmRule = new EvenRhythmRule();
    }

    @Test
    public void testName() throws Exception {
        int size = 4;
        Member perfect = createMember(size, 0, 2, 4, 6);

        FitnessValue result = evenRhythmRule.apply(perfect);

        assertThat(result).isEqualTo(FitnessValue.max());
    }

    @Test
    public void worst() throws Exception {
        int size = 4;
        Member perfect = createMember(size, 1, 3, 4, 5);

        FitnessValue worst = evenRhythmRule.apply(perfect);

        assertThat(worst).isEqualTo(FitnessValue.min());
    }

    @Test
    public void half() throws Exception {
        int size = 4;
        Member half = createMember(size, 0, 1, 2, 3);

        FitnessValue result = evenRhythmRule.apply(half);

        assertThat(result.get()).isEqualTo(50);
    }

    @Test
    public void edge_case() {
        int size = 16;
        Member half = createMember(size, 0, 2, 5, 8, 10, 12, 14);

        FitnessValue result = evenRhythmRule.apply(half);

        assertThat(result.get()).isEqualTo(86);
    }

    private Member createMember(int size, Integer... notePositions) {
        List<NoteValue> noteValueList = new ArrayList<NoteValue>(size);
        List<NoteType> noteTypesList = new ArrayList<NoteType>(size);
        List<Integer> notePositionsList = Arrays.asList(notePositions);
        for (int index = 0; index < size; index++) {
            noteValueList.add(NoteValue.newInstance(index));
            if (notePositionsList.contains(index)) {
                noteTypesList.add(NoteType.NOTE);
            } else {
                noteTypesList.add(NoteType.REST);
            }
        }
        return new Member(noteValueList, noteTypesList, new Member.Controller(new NoteOnFilter()));
    }

}
