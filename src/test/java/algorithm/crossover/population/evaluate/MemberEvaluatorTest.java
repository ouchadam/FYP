package algorithm.crossover.population.evaluate;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;

import algorithm.Member;
import algorithm.crossover.binary.Binary;
import algorithm.crossover.population.evaluate.fitness.FitnessRule;
import algorithm.crossover.population.evaluate.fitness.FitnessValue;
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
        List<Binary> arrayList = new ArrayList<Binary>();
        arrayList.add(new Binary("01"));
        return new Member(arrayList);
    }
}
