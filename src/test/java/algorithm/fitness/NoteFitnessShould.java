package algorithm.fitness;

import helper.TestWithMocks;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class NoteFitnessShould extends TestWithMocks {

    private NoteFitness noteFitness;

    @Override
    protected void before() {
        noteFitness = new NoteFitness();
    }

    @Test
    public void mark_10_as_maximum_fitness() {
        int perfectValue = 10;
        FitnessValue fitnessValue = noteFitness.evaluate(perfectValue);

        assertThat(fitnessValue.isMax()).isTrue();
    }


    @Test
    public void mark_any_number_other_than_10_as_minimum_fitness() {
        int badValue = 0;
        FitnessValue fitnessValue = noteFitness.evaluate(badValue);

        assertThat(fitnessValue.isMin()).isTrue();
    }
}
