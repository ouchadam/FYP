package com.ouchadam.fyp.algorithm.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;
import helper.MemberRuleTest;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class IntervalRangeRuleTest extends MemberRuleTest {

    private IntervalRangeRule intervalRangeRule;

    @Override
    protected void before() {
        intervalRangeRule = new IntervalRangeRule(4);
    }

    @Test
    public void be_max_fitness_when_the_first_jump_matches_followed_by_steps() {
        int[] validNoteSequence = new int[] {60, 66, 67, 68, 69, 70};

        Member member = createMember(validNoteSequence);

        FitnessValue fitnessValue = intervalRangeRule.apply(member);

        assertThat(fitnessValue).isEqualTo(FitnessValue.max());
    }

    @Test
    public void be_minimum_fitness_when_1_note_is_provided() {
        int[] invalidNoteSequence = new int[] {60};

        Member member = createMember(invalidNoteSequence);

        FitnessValue fitnessValue = intervalRangeRule.apply(member);

        assertThat(fitnessValue).isEqualTo(FitnessValue.min());
    }

}
