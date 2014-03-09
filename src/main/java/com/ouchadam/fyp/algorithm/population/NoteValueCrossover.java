package com.ouchadam.fyp.algorithm.population;

import com.ouchadam.fyp.algorithm.NoteValue;
import com.ouchadam.fyp.algorithm.crossover.Crossover;
import com.ouchadam.fyp.algorithm.crossover.binary.Binary;

public class NoteValueCrossover implements Crossover<NoteValue> {

    private final Crossover<Binary> binaryCrossover;

    public NoteValueCrossover(Crossover<Binary> binaryCrossover) {
        this.binaryCrossover = binaryCrossover;
    }

    @Override
    public NoteValue crossover(NoteValue parentX, NoteValue parentY) {
        return new NoteValue(binaryCrossover.crossover(parentX.binary(), parentY.binary()));
    }
}
