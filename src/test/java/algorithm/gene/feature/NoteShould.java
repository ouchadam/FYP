package algorithm.gene.feature;

import algorithm.fitness.FitnessEvaluator;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;

public class NoteShould extends TestWithMocks {

    @Mock FitnessEvaluator<Integer> evaluator;

    @Test
    public void provide_the_value_in_toString() throws Exception {
        int value = 10;
        Note note_1 = new Note(value, evaluator);

        assertThat(note_1.toString()).isEqualTo(String.valueOf(value));
    }

}
