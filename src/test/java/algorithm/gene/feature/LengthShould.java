package algorithm.gene.feature;

import algorithm.fitness.FitnessEvaluator;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;

public class LengthShould extends TestWithMocks {

    @Mock FitnessEvaluator<Integer> evaluator;

    @Test
    public void provide_the_value_in_toString() throws Exception {
        int value = 10;
        Length length = new Length(value, evaluator);

        assertThat(length.toString()).isEqualTo(String.valueOf(value));
    }

}
