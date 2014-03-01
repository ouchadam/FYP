package com.ouchadam.fyp.algorithm.population.evaluate;

import java.util.ArrayList;
import java.util.List;

import com.ouchadam.fyp.algorithm.Note;
import org.junit.Test;
import org.mockito.Mock;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import com.ouchadam.fyp.algorithm.population.Evaluation;
import com.ouchadam.fyp.algorithm.population.Population;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessEvaluator;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessRule;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessValue;
import helper.MemberHelper;
import helper.TestWithMocks;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PopulationEvaluatorTest extends TestWithMocks {

    @Mock
    FitnessFactory fitnessFactory;
    private PopulationEvaluator populationEvaluator;

    @Override
    protected void before() {
        populationEvaluator = new PopulationEvaluator(fitnessFactory);
    }

    @Test
    public void return_max_fitness_when_all_members_are_max_fitness() {
        forcePerfectFitness();
        Population population = createRandomPopulation();

        Evaluation evaluate = populationEvaluator.evaluate(population);

        assertThat(evaluate.fitnessValue(0)).isEqualTo(FitnessValue.max());
    }

    private void forcePerfectFitness() {
        FitnessEvaluator<Member> mockEvaluator = mock(FitnessEvaluator.class);
        when(mockEvaluator.evaluate(any(Member.class), (FitnessRule<Member>) anyVararg())).thenReturn(FitnessValue.max());
        when(fitnessFactory.member()).thenReturn(mockEvaluator);
    }

    private Population createRandomPopulation() {
        List<Member> members = new ArrayList<Member>();
        members.add(MemberHelper.createRandom());
        members.add(MemberHelper.createRandom());
        members.add(MemberHelper.createRandom());
        members.add(MemberHelper.createRandom());
        return new Population(members);
    }

}
