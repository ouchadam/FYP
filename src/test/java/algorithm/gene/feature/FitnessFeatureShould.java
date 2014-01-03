package algorithm.gene.feature;

import algorithm.fitness.FitnessEvaluator;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class FitnessFeatureShould extends TestWithMocks {

    @Mock FitnessEvaluator<Integer> evaluator;

    @Test
    public void equal_when_values_are_the_same() {
        int value = 10;
        StubFitnessFeature feature_1 = new StubFitnessFeature(value, evaluator);
        StubFitnessFeature feature_2 = new StubFitnessFeature(value, evaluator);

        assertThat(feature_1).isEqualTo(feature_2);
        assertThat(feature_1.hashCode()).isEqualTo(feature_2.hashCode());
    }

    @Test
    public void store_the_intial_given_value() {
        int value = 120;
        StubFitnessFeature feature = new StubFitnessFeature(value, evaluator);

        assertThat(value).isEqualTo(feature.getValue());
    }

    @Test
    public void use_the_evaluator_to_get_the_fitness_value() throws Exception {
        int value = 1;
        StubFitnessFeature feature = new StubFitnessFeature(value, evaluator);

        feature.getFitness();

        verify(evaluator).evaluate(value);
    }

    private static class StubFitnessFeature extends FitnessFeature<Integer> {
        protected StubFitnessFeature(Integer value, FitnessEvaluator<Integer> evaluator) {
            super(value, evaluator);
        }
    }

}
