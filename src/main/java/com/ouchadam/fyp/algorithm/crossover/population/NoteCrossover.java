package com.ouchadam.fyp.algorithm.crossover.population;

import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.crossover.Crossover;
import com.ouchadam.fyp.algorithm.crossover.binary.Binary;

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
