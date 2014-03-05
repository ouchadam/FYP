package com.ouchadam.fyp.algorithm.population.evaluate.fitness;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class FixedNoteRuleTest {

    @Test
    public void testName() {
        FixedNoteRule fixedNoteRule = new FixedNoteRule(new FixedNoteRule.FixedNote(60));

        FitnessValue result = fixedNoteRule.apply(createMember(60));

        assertThat(result.get()).isEqualTo(100);
    }

    private Member createMember(int noteValue) {
        List<Note> notes = new ArrayList<Note>(2);
        notes.add(new Note(new Binary(Integer.toBinaryString(noteValue))));
        return new Member(notes, new Member.Controller());
    }

}
