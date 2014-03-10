package com.ouchadam.fyp.algorithm.evaluate.fitness;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.NoteType;
import com.ouchadam.fyp.algorithm.NoteValue;
import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import com.ouchadam.fyp.algorithm.evaluate.rule.FitnessRule;
import com.ouchadam.fyp.algorithm.evaluate.rule.NoteRangeRule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import helper.TestWithMocks;

import static org.fest.assertions.api.Assertions.assertThat;

public class NoteValueRangeRuleTest extends TestWithMocks {

    private FitnessRule<Member> noteRangeRule;

    private static final int RANGE = 12;

    @Override
    protected void before() {
        noteRangeRule = new NoteRangeRule(RANGE);
    }

    @Test
    public void perfect_input() throws Exception {
        int highest = 100;
        int lowest = highest - (RANGE * 2);

        Member perfect = createMember(lowest, highest, lowest + RANGE, highest - RANGE);

        FitnessValue fitnessValue = noteRangeRule.apply(perfect);

        assertThat(fitnessValue).isEqualTo(FitnessValue.max());
    }

    @Test
    public void bad_input() throws Exception {
        int range = 12;
        int base = 60;
        NoteRangeRule noteRangeRule = new NoteRangeRule(range);

        Member bad = createMember(base - 60, base, base + 60);

        FitnessValue fitnessValue = noteRangeRule.apply(bad);

        assertThat(fitnessValue).isNotEqualTo(FitnessValue.max());
    }

    @Test
    public void cap_at_0_even_when_member_is_the_worst() throws Exception {
        int range = 12;
        NoteRangeRule noteRangeRule = new NoteRangeRule(range);

        Member bad = createMember(0, 127, 0 , 127);

        FitnessValue fitnessValue = noteRangeRule.apply(bad);

        assertThat(fitnessValue).isEqualTo(FitnessValue.min());
    }

    private Member createMember(int... noteValues) {
        List<NoteValue> notes = new ArrayList<NoteValue>(noteValues.length);
        List<NoteType> types = new ArrayList<NoteType>(noteValues.length);
        for (int noteValue : noteValues) {
            notes.add(new NoteValue(new Binary(Integer.toBinaryString(noteValue))));
            types.add(NoteType.NOTE);
        }
        return new Member(notes, types, new Member.Controller());
    }
}
