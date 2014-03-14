package com.ouchadam.fyp.algorithm.evaluate;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.population.Evaluation;
import com.ouchadam.fyp.algorithm.population.Population;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessEvaluator;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;
import com.ouchadam.fyp.presentation.tab.rule.RuleContainer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;

import helper.MemberHelper;
import helper.TestWithMocks;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PopulationEvaluatorTest extends TestWithMocks {

    @Mock FitnessFactory fitnessFactory;
    @Mock List<RuleContainer<Member>> rules;
    private PopulationEvaluator populationEvaluator;

    @Override
    protected void before() {
        populationEvaluator = new PopulationEvaluator(fitnessFactory, rules);
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
        when(mockEvaluator.evaluate(any(Member.class), anyList())).thenReturn(FitnessValue.max());
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
