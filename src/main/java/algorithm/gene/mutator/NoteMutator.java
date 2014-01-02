package algorithm.gene.mutator;

import algorithm.gene.Mutator;
import algorithm.gene.feature.Note;

public class NoteMutator implements Mutator<Note> {

    static final int MUTATION_RANGE = 10;
    static final int ZERO_RANGE_OFFSET = 1;

    private final RandomNumberPicker random;

    public NoteMutator(RandomNumberPicker random) {
        this.random = random;
    }

    @Override
    public Note mutate(Note feature) {
        Integer value = feature.getValue();
        int mutatedNoteValue = value + createRandomOffset();
        return new Note(mutatedNoteValue);
    }

    private int createRandomOffset() {
        // TODO currently guarrantees a mutation, maybe this isn't wanted, the offset will fix that
        return random.getNumberFromRange(MUTATION_RANGE, ZERO_RANGE_OFFSET);
    }

}
