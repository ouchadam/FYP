package algorithm.gene.feature;

import algorithm.fitness.FitnessEvaluator;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;

public class OctaveShould extends TestWithMocks {

    @Mock FitnessEvaluator<Octave> evaluator;

    @Test
    public void equal_when_values_are_the_same() {
        Octave octave_1 = new Octave(3, evaluator);
        Octave octave_2 = new Octave(3, evaluator);

        assertThat(octave_1).isEqualTo(octave_2);
        assertThat(octave_1.hashCode()).isEqualTo(octave_2.hashCode());
    }


    @Test
    public void store_the_intial_given_value() {
        int octaveValue = 2;
        Octave length = new Octave(octaveValue, evaluator);

        assertThat(octaveValue).isEqualTo(Integer.parseInt(length.toString()));
    }

}
