package algorithm.fitness;

import algorithm.gene.Gene;
import algorithm.gene.feature.Note;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class NoteFitnessShould extends TestWithMocks {

    private static final int BAD_VALUE = 0;
    private final static int PERFECT_VALUE = 10;
    private NoteFitness noteFitness;

    @Mock Gene<Note, Integer> gene;

    @Override
    protected void before() {
        noteFitness = new NoteFitness();
    }

    @Test
    public void mark_10_as_maximum_fitness() {
        when(gene.getValue()).thenReturn(PERFECT_VALUE);

        FitnessValue fitnessValue = noteFitness.evaluate(gene);

        assertThat(fitnessValue.isMax()).isTrue();
    }

    @Test
    public void mark_any_number_other_than_10_as_minimum_fitness() {
        when(gene.getValue()).thenReturn(BAD_VALUE);

        FitnessValue fitnessValue = noteFitness.evaluate(gene);

        assertThat(fitnessValue.isMin()).isTrue();
    }

}
