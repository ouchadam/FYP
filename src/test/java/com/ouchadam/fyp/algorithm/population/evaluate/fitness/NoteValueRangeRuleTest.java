package com.ouchadam.fyp.algorithm.population.evaluate.fitness;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.NoteType;
import com.ouchadam.fyp.algorithm.NoteValue;
import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import com.ouchadam.fyp.algorithm.population.evaluate.rule.NoteRangeRule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import helper.TestWithMocks;

import static org.fest.assertions.api.Assertions.assertThat;

public class NoteValueRangeRuleTest extends TestWithMocks {

    private NoteRangeRule noteRangeRule;

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

        Member perfect = createMember(base, base + (range + 1), base - (range + 1), base / 2);

        FitnessValue fitnessValue = noteRangeRule.apply(perfect);

        assertThat(fitnessValue).isNotEqualTo(FitnessValue.max());
    }

    private Member createMember(int... noteValues) {
        List<NoteValue> notes = new ArrayList<NoteValue>(noteValues.length);
        for (int noteValue : noteValues) {
            notes.add(new NoteValue(new Binary(Integer.toBinaryString(noteValue))));
        }
        return new Member(notes, new ArrayList<NoteType>(), new Member.Controller());
    }
}
