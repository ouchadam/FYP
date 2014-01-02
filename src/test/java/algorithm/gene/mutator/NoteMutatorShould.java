package algorithm.gene.mutator;

import algorithm.gene.feature.Note;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class NoteMutatorShould extends TestWithMocks {

    @Mock RandomNumberPicker random;
//
//    @Test
//    public void use_a_random_integer_when_mutating() throws Exception {
//        NoteMutator noteMutator = new NoteMutator(random);
//        Note note = new Note(10);
//
//        noteMutator.mutate(note);
//
//        verify(random).nextInt(anyInt());
//    }
//

    @Test
    public void some_offset() throws Exception {
        int originalValue = 10;
        int mutationValue = 1;
        when(random.getNumberFromRange(anyInt(), anyInt())).thenReturn(mutationValue);
        NoteMutator noteMutator = new NoteMutator(random);
        Note note = new Note(originalValue);

        Note mutatedNote = noteMutator.mutate(note);

        assertThat(mutatedNote.getValue()).isEqualTo(originalValue + mutationValue);
    }

}
