package algorithm.gene.mutator;

import algorithm.fitness.FitnessEvaluator;
import algorithm.gene.Feature;
import algorithm.gene.Mutator;
import algorithm.gene.feature.Note;
import algorithm.gene.feature.NoteCreator;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NoteMutatorShould extends TestWithMocks {

    @Mock RandomNumberPicker randomNotePicker;
    @Mock NoteCreator noteCreator;
    @Mock Note note;

    Mutator<Note> noteMutator;
    static final int ORIGINAL_VALUE = 10;

    @Override
    protected void before() {
        when(note.getValue()).thenReturn(ORIGINAL_VALUE);
        when(noteCreator.createNote(anyInt())).thenAnswer(onCreateNote);

        noteMutator = new NoteMutator(randomNotePicker, noteCreator);
    }

    @Test
    public void use_a_random_integer_when_mutating() {
        noteMutator.mutate(note);

        verify(randomNotePicker).getNumberFromRange(anyInt(), anyInt());
    }

    @Test
    public void be_the_sum_of_the_original_value_and_mutation() {
        int mutationValue = -1;
        when(randomNotePicker.getNumberFromRange(anyInt(), anyInt())).thenReturn(mutationValue);

        Feature<Integer> mutatedNote = noteMutator.mutate(note);

        assertThat(mutatedNote.getValue()).isEqualTo(ORIGINAL_VALUE + mutationValue);
    }
    
    private final Answer<Note> onCreateNote = new Answer<Note>() {
        @Override
        public Note answer(InvocationOnMock invocation) throws Throwable {
            Integer noteValue = (Integer) invocation.getArguments()[0];
            Note note = mock(Note.class);
            when(note.getValue()).thenReturn(noteValue);
            return note;
        }
    };

}
