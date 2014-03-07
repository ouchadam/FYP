package com.ouchadam.fyp.algorithm.population.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessValue;
import com.ouchadam.fyp.analysis.Key;
import com.ouchadam.fyp.presentation.ScaleCreator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import helper.TestWithMocks;

import static org.fest.assertions.api.Assertions.assertThat;

public class FixedKeySignatureRuleTest extends TestWithMocks {

    private FixedKeySignatureRule fixedKeySignatureRule;

    @Override
    protected void before() {
    }

    @Test
    public void testName() throws Exception {
        int[] cmajor = new int[] {60, 62, 64, 65, 67, 69, 11};
        Key cKey = Key.C;

        fixedKeySignatureRule = new FixedKeySignatureRule(cKey, new ScaleCreator());

        Member member = createMember(cmajor);

        FitnessValue fitnessValue = fixedKeySignatureRule.apply(member);

        assertThat(fitnessValue).isEqualTo(FitnessValue.max());
    }

    private Member createMember(int[] noteValues) {
        List<Note> notes = new ArrayList<Note>();
        for (int noteValue : noteValues) {
            notes.add(Note.newInstance(noteValue));
        }
        return new Member(notes, new Member.Controller());
    }
}
