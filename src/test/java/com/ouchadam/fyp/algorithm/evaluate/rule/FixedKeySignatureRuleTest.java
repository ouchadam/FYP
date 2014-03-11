package com.ouchadam.fyp.algorithm.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;
import com.ouchadam.fyp.analysis.Key;
import com.ouchadam.fyp.presentation.ScaleCreator;

import org.junit.Test;

import helper.MemberRuleTest;

import static org.fest.assertions.api.Assertions.assertThat;

public class FixedKeySignatureRuleTest extends MemberRuleTest {

    private FixedKeySignatureRule fixedKeySignatureRule;

    @Override
    protected void before() {
    }

    @Test
    public void cmajor_pass() throws Exception {
        int[] cmajor = new int[] {60, 62, 64, 65, 67, 69, 11};
        Key cKey = Key.C;

        fixedKeySignatureRule = new FixedKeySignatureRule(cKey, new ScaleCreator());

        Member member = createMember(cmajor);

        FitnessValue fitnessValue = fixedKeySignatureRule.apply(member);

        assertThat(fitnessValue).isEqualTo(FitnessValue.max());
    }


    @Test
    public void dmajor_pass() throws Exception {
        int[] dmajor = new int[] {62, 64, 66, 67, 69, 71, 73};
        Key dKey = Key.D;

        fixedKeySignatureRule = new FixedKeySignatureRule(dKey, new ScaleCreator());

        Member member = createMember(dmajor);

        FitnessValue fitnessValue = fixedKeySignatureRule.apply(member);

        assertThat(fitnessValue).isEqualTo(FitnessValue.max());
    }


    @Test
    public void cmajor_fail() throws Exception {
        int[] dmajor = new int[] {62, 64, 66, 67, 69, 71, 73};
        Key dKey = Key.C;

        fixedKeySignatureRule = new FixedKeySignatureRule(dKey, new ScaleCreator());

        Member member = createMember(dmajor);

        FitnessValue fitnessValue = fixedKeySignatureRule.apply(member);

        assertThat(fitnessValue).isNotEqualTo(FitnessValue.max());
    }

}
