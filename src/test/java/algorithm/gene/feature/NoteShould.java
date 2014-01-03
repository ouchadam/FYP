package algorithm.gene.feature;

import algorithm.fitness.FitnessEvaluator;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;

public class NoteShould extends TestWithMocks {

    @Mock FitnessEvaluator<Integer> evaluator;

    @Test
    public void equal_when_values_are_the_same() {
        Note note_1 = new Note(10, evaluator);
        Note note_2 = new Note(10, evaluator);

        assertThat(note_1).isEqualTo(note_2);
        assertThat(note_1.hashCode()).isEqualTo(note_2.hashCode());
    }


    @Test
    public void store_the_intial_given_value() {
        int noteValue = 120;
        Note note = new Note(noteValue, evaluator);

        assertThat(noteValue).isEqualTo(Integer.parseInt(note.toString()));
    }

}
