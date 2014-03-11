package com.ouchadam.fyp.algorithm.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.NoteType;
import com.ouchadam.fyp.algorithm.NoteValue;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;
import com.ouchadam.fyp.presentation.NoteOnFilter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import helper.TestWithMocks;

import static org.fest.assertions.api.Assertions.assertThat;

public class NoteDiversityRuleTest extends TestWithMocks {

    @Test
    public void give_max_fitness_when_diversity_count_is_met() throws Exception {
        FitnessRule<Member> noteDiversityRule = new NoteDiversityRule(4);

        Member diverse = createMember(0, 1 , 2 , 3);

        FitnessValue fitness = noteDiversityRule.apply(diverse);

        assertThat(fitness).isEqualTo(FitnessValue.max());
    }

    @Test
    public void give_min_fitness_when_diversity_count_is_completely_failed() throws Exception {
        FitnessRule<Member> noteDiversityRule = new NoteDiversityRule(4);

        Member failed = createMember(0, 0, 0, 0);

        FitnessValue fitness = noteDiversityRule.apply(failed);

        assertThat(fitness).isEqualTo(FitnessValue.min());
    }

    @Test
    public void give_half_fitness_when_diversity_count_is_half_diverse() throws Exception {
        FitnessRule<Member> noteDiversityRule = new NoteDiversityRule(4);

        Member halfDiverse = createMember(0, 0, 0, 2);

        FitnessValue fitness = noteDiversityRule.apply(halfDiverse);

        assertThat(fitness.get()).isEqualTo(50);
    }

    @Test
    public void give_max_fitness_when_diversity_count_is_above_wanted() throws Exception {
        FitnessRule<Member> noteDiversityRule = new NoteDiversityRule(4);

        Member diverse = createMember(0, 1 , 2 , 3, 4, 5, 6);

        FitnessValue fitness = noteDiversityRule.apply(diverse);

        assertThat(fitness).isEqualTo(FitnessValue.max());
    }

    private Member createMember(int... notes) {
        List<NoteValue> noteValues = new ArrayList<NoteValue>(notes.length);
        List<NoteType> noteTypes = new ArrayList<NoteType>(notes.length);
        for (int note : notes) {
            noteValues.add(NoteValue.newInstance(note));
            noteTypes.add(NoteType.NOTE);
        }
        return new Member(noteValues, noteTypes, new Member.Controller(new NoteOnFilter()));
    }

}
