package com.ouchadam.fyp.algorithm.population.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessValue;
import helper.MemberRuleTest;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class IntervalRangeRuleTest extends MemberRuleTest {

    private IntervalRangeRule intervalRangeRule;

    @Override
    protected void before() {
        intervalRangeRule = new IntervalRangeRule();
    }

    @Test
    public void testName() throws Exception {

        int[] validNoteSequence = new int[] {60, 66, 67, 68, 69, 70};

        Member member = createMember(validNoteSequence);

        FitnessValue fitnessValue = intervalRangeRule.apply(member);

        assertThat(fitnessValue).isEqualTo(FitnessValue.max());
    }
}
