package algorithm.crossover.population;

import algorithm.Note;
import algorithm.crossover.Crossover;
import algorithm.crossover.binary.Binary;

public class NoteCrossover implements Crossover<Note> {

    private final Crossover<Binary> binaryCrossover;

    public NoteCrossover(Crossover<Binary> binaryCrossover) {
        this.binaryCrossover = binaryCrossover;
    }

    @Override
    public Note crossover(Note parentX, Note parentY) {
        return new Note(binaryCrossover.crossover(parentX.binary(), parentY.binary()));
    }
}
