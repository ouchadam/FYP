package com.ouchadam.fyp.algorithm.population;

import com.ouchadam.fyp.algorithm.evaluate.OrderedPopulation;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EvaluationTest extends TestWithMocks {

    @Mock OrderedPopulation orderedPopulation;
    private Evaluation evaluation;

    @Override
    protected void before() {
        evaluation = new Evaluation(orderedPopulation);
    }

    @Test
    public void setting_pass_should_give_the_pass_result_type() {
        evaluation.setPass();

        assertThat(evaluation.getResultType()).isEqualTo(Evaluation.ResultType.PASS);
    }

    @Test
    public void setting_fail_should_give_the_fail_result_type() {
        evaluation.setFail();

        assertThat(evaluation.getResultType()).isEqualTo(Evaluation.ResultType.FAIL);
    }

    @Test (expected = IllegalAccessError.class)
    public void fetching_result_type_without_setting_one_should_throw() {
        evaluation.getResultType();
    }

    @Test
    public void meets_fitness_should_return_true_when_value_is_higher_or_equal() {
        int resultFitnessValue = 80;
        int wantedFitness = 50;
        when(orderedPopulation.getFitness(0)).thenReturn(new FitnessValue(resultFitnessValue));

        assertThat(evaluation.meetsWantedFitness(wantedFitness)).isTrue();
    }

    @Test
    public void ask_ordered_population_to_create_population() {
        evaluation.population();

        verify(orderedPopulation).asPopulation();
    }

}
