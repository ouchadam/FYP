package algorithm.gene.mutator;

import algorithm.gene.Feature;
import algorithm.gene.Mutator;
import algorithm.gene.feature.Note;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NoteMutatorShould extends TestWithMocks {

    @Mock RandomNumberPicker randomNotePicker;
    Mutator<Note> noteMutator;

    @Override
    protected void before() {
        noteMutator = new NoteMutator(randomNotePicker);
    }

    @Test
    public void use_a_random_integer_when_mutating() throws Exception {
        Note note = new Note(10);

        noteMutator.mutate(note);

        verify(randomNotePicker).getNumberFromRange(anyInt(), anyInt());
    }

    @Test
    public void be_the_sum_of_the_original_value_and_mutation() throws Exception {
        int originalValue = 10;
        int mutationValue = -1;
        when(randomNotePicker.getNumberFromRange(anyInt(), anyInt())).thenReturn(mutationValue);
        noteMutator = new NoteMutator(randomNotePicker);
        Note note = new Note(originalValue);

        Feature<Integer> mutatedNote = noteMutator.mutate(note);

        assertThat(mutatedNote.getValue()).isEqualTo(originalValue + mutationValue);
    }

}
