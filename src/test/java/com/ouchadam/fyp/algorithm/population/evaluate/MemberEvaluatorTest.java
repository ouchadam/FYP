package com.ouchadam.fyp.algorithm.population.evaluate;

import java.util.ArrayList;
import java.util.List;

import com.ouchadam.fyp.algorithm.Note;
import org.junit.Test;
import org.mockito.Mock;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessRule;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessValue;
import helper.TestWithMocks;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MemberEvaluatorTest extends TestWithMocks {

    @Mock FitnessRule<Member> fitnessRule;

    @Override
    protected void before() {
        when(fitnessRule.apply(any(Member.class))).thenReturn(FitnessValue.max());
    }

    @Test
    public void make_use_of_the_supplied_rules() {
        MemberEvaluator memberEvaluator = new MemberEvaluator();

        memberEvaluator.evaluate(createMember(), fitnessRule);

        verify(fitnessRule).apply(any(Member.class));
    }

    private Member createMember() {
        List<Note> arrayList = new ArrayList<Note>();
        arrayList.add(new Note(new Binary("01")));
        return new Member(arrayList, new Member.Controller());
    }
}
