package algorithm.gene.feature;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class NoteShould {

    @Test
    public void equal_when_values_are_the_same() {
        Note note_1 = new Note(10);
        Note note_2 = new Note(10);

        assertThat(note_1).isEqualTo(note_2);
        assertThat(note_1.hashCode()).isEqualTo(note_2.hashCode());
    }


    @Test
    public void store_the_intial_given_value() {
        int noteValue = 120;
        Note note = new Note(noteValue);

        assertThat(noteValue).isEqualTo(Integer.parseInt(note.toString()));
    }

}
