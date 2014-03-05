package com.ouchadam.fyp.algorithm.population.evaluate.fitness;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import helper.TestWithMocks;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class NoteRangeRuleTest extends TestWithMocks {

    @Test
    public void perfect_input() throws Exception {
        int range = 12;
        int base = 60;
        NoteRangeRule noteRangeRule = new NoteRangeRule(range, base);

        Member perfect = createMember(base, base + range, base - range, base + (range / 2), base - (range / 2));

        FitnessValue fitnessValue = noteRangeRule.apply(perfect);

        assertThat(fitnessValue).isEqualTo(FitnessValue.max());
    }

    @Test
    public void bad_input() throws Exception {
        int range = 12;
        int base = 60;
        NoteRangeRule noteRangeRule = new NoteRangeRule(range, base);

        Member perfect = createMember(base, base + (range + 1), base - (range + 1), base / 2);

        FitnessValue fitnessValue = noteRangeRule.apply(perfect);

        assertThat(fitnessValue).isNotEqualTo(FitnessValue.max());
    }

    private Member createMember(int... noteValues) {
        List<Note> notes = new ArrayList<Note>(noteValues.length);
        for (int noteValue : noteValues) {
            notes.add(new Note(new Binary(Integer.toBinaryString(noteValue))));
        }
        return new Member(notes, new Member.Controller());
    }
}
