package com.ouchadam.fyp.algorithm.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.NoteType;
import com.ouchadam.fyp.algorithm.NoteValue;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;
import com.ouchadam.fyp.presentation.NoteOnFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class MinimumNoteRuleTest {

    static final int MINIMUM_NOTE_COUNT = 4;
    private FitnessRule<Member> minimumNoteRule;

    @Before
    public void setUp() {
        minimumNoteRule = new MinimumNoteRule(MINIMUM_NOTE_COUNT);
    }

    @Test
    public void best() {
        Member perfect = createMember(MINIMUM_NOTE_COUNT, 0, 1, 2 , 3 , 4);

        FitnessValue result = minimumNoteRule.apply(perfect);

        assertThat(result).isEqualTo(FitnessValue.max());
    }

    @Test
    public void worst() {
        Member worst = createMember(MINIMUM_NOTE_COUNT);

        FitnessValue result = minimumNoteRule.apply(worst);

        assertThat(result).isEqualTo(FitnessValue.min());
    }

    @Test
    public void half() {
        Member half = createMember(MINIMUM_NOTE_COUNT, 0, 1);

        FitnessValue result = minimumNoteRule.apply(half);

        assertThat(result.get()).isEqualTo(50);
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
