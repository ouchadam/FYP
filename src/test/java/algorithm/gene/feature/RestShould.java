package algorithm.gene.feature;

import algorithm.fitness.FitnessEvaluator;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;

public class RestShould extends TestWithMocks {

    @Mock FitnessEvaluator<Rest.Value> evaluator;

    @Test
    public void equal_when_values_are_the_same() {
        Rest rest_1 = new Rest(Rest.Value.REST, evaluator);
        Rest rest_2 = new Rest(Rest.Value.REST, evaluator);

        assertThat(rest_1).isEqualTo(rest_2);
        assertThat(rest_1.hashCode()).isEqualTo(rest_2.hashCode());
    }


    @Test
    public void represent_its_value_as_a_1_when_REST() {
        Rest rest = new Rest(Rest.Value.REST, evaluator);

        int value = Integer.parseInt(rest.toString());

        assertThat(value).isEqualTo(1);
    }


    @Test
    public void represent_its_value_as_a_0_when_HOLD() {
        Rest rest = new Rest(Rest.Value.HOLD, evaluator);

        int value = Integer.parseInt(rest.toString());

        assertThat(value).isEqualTo(0);
    }
}
