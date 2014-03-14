package com.ouchadam.fyp.algorithm.evaluate;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.NoteType;
import com.ouchadam.fyp.algorithm.NoteValue;
import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;
import com.ouchadam.fyp.algorithm.evaluate.rule.FitnessRule;
import com.ouchadam.fyp.presentation.midi.NoteOnFilter;
import com.ouchadam.fyp.presentation.tab.rule.RuleContainer;
import com.ouchadam.fyp.presentation.tab.rule.RuleName;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;

import helper.TestWithMocks;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MemberEvaluatorTest extends TestWithMocks {

    @Mock FitnessRule<Member> fitnessRule;
    List<RuleContainer<Member>> rules;

    @Override
    protected void before() {
        rules = new ArrayList<RuleContainer<Member>>();
        rules.add(new RuleContainer<Member>(fitnessRule, RuleName.KEY, 0));
        when(fitnessRule.apply(any(Member.class))).thenReturn(FitnessValue.max());
    }

    @Test
    public void make_use_of_the_supplied_rules() {
        MemberEvaluator memberEvaluator = new MemberEvaluator();

        memberEvaluator.evaluate(createMember(), rules);

        verify(fitnessRule).apply(any(Member.class));
    }

    private Member createMember() {
        List<NoteValue> arrayList = new ArrayList<NoteValue>();
        arrayList.add(new NoteValue(new Binary("01")));
        return new Member(arrayList, new ArrayList<NoteType>(), new Member.Controller(new NoteOnFilter()));
    }
}
