package algorithm.gene.feature;

import algorithm.fitness.EvaluatorType;
import helper.TestWithMocks;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class FitnessFeatureShould extends TestWithMocks {

    @Test
    public void equal_when_values_are_the_same() {
        int value = 10;
        StubBaseFeature feature_1 = new StubBaseFeature(value);
        StubBaseFeature feature_2 = new StubBaseFeature(value);

        assertThat(feature_1).isEqualTo(feature_2);
        assertThat(feature_1.hashCode()).isEqualTo(feature_2.hashCode());
    }

    @Test
    public void store_the_intial_given_value() {
        int value = 120;
        StubBaseFeature feature = new StubBaseFeature(value);

        assertThat(value).isEqualTo(feature.getValue());
    }

    private static class StubBaseFeature extends BaseFeature<Integer> {
        protected StubBaseFeature(Integer value) {
            super(value, EvaluatorType.NOTE);
        }
    }

}
