package algorithm.gene.mutator;

import algorithm.gene.Mutator;
import algorithm.gene.feature.Note;
import algorithm.gene.feature.NoteCreator;

public class NoteMutator implements Mutator<Note> {

    static final int MUTATION_RANGE = 10;
    static final int ZERO_RANGE_OFFSET = 1;

    private final RandomNumberPicker random;
    private NoteCreator noteCreator;

    public NoteMutator(RandomNumberPicker random, NoteCreator noteCreator) {
        this.random = random;
        this.noteCreator = noteCreator;
    }

    @Override
    public Note mutate(Note feature) {
        Integer value = feature.getValue();
        int mutatedNoteValue = value + createRandomOffset();
        return noteCreator.createNote(mutatedNoteValue);
    }

    private int createRandomOffset() {
        // TODO currently guarrantees a mutation, maybe this isn't wanted, the offset will fix that
        return random.getNumberFromRange(MUTATION_RANGE, ZERO_RANGE_OFFSET);
    }

}
