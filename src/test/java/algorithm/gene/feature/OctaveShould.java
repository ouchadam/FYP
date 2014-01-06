package algorithm.gene.feature;

import algorithm.fitness.FitnessEvaluator;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;

public class OctaveShould extends TestWithMocks {

    @Test
    public void provide_the_value_in_toString() throws Exception {
        int value = 10;
        Octave octave = new Octave(value);

        assertThat(octave.toString()).isEqualTo(String.valueOf(value));
    }

}
