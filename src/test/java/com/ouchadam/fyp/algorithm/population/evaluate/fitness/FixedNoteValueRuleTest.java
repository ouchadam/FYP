package com.ouchadam.fyp.algorithm.population.evaluate.fitness;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.NoteType;
import com.ouchadam.fyp.algorithm.NoteValue;
import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import com.ouchadam.fyp.algorithm.population.evaluate.rule.FixedNoteRule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class FixedNoteValueRuleTest {

    @Test
    public void testName() {
        FixedNoteRule fixedNoteRule = new FixedNoteRule(new FixedNoteRule.FixedNote(60));

        FitnessValue result = fixedNoteRule.apply(createMember(60));

        assertThat(result.get()).isEqualTo(100);
    }

    private Member createMember(int noteValue) {
        List<NoteValue> noteValues = new ArrayList<NoteValue>(1);
        List<NoteType> noteTypes = new ArrayList<NoteType>(1);
        noteValues.add(new NoteValue(new Binary(Integer.toBinaryString(noteValue))));
        noteTypes.add(NoteType.NOTE);
        return new Member(noteValues, noteTypes, new Member.Controller());
    }

}
