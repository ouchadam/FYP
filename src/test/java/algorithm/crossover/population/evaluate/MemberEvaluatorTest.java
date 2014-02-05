package algorithm.crossover.population.evaluate;

import algorithm.Member;
import algorithm.crossover.binary.Binary;
import algorithm.crossover.population.evaluate.fitness.FitnessRule;
import algorithm.crossover.population.evaluate.fitness.FitnessValue;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MemberEvaluatorTest extends TestWithMocks {

    @Mock FitnessRule<Binary> fitnessRule;

    @Override
    protected void before() {
        when(fitnessRule.apply(any(Binary.class))).thenReturn(FitnessValue.max());
    }

    @Test
    public void make_use_of_the_supplied_rules() {
        MemberEvaluator memberEvaluator = new MemberEvaluator(fitnessRule, createMember());

        memberEvaluator.evaluate();

        verify(fitnessRule).apply(any(Binary.class));
    }

    private Member createMember() {
        List<Binary> arrayList = new ArrayList<Binary>();
        arrayList.add(new Binary("01"));
        return new Member(arrayList);
    }
}
