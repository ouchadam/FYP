package com.ouchadam.fyp.algorithm.population;

import com.ouchadam.fyp.algorithm.NoteType;
import com.ouchadam.fyp.algorithm.NoteValue;
import com.ouchadam.fyp.algorithm.crossover.Crossover;
import com.ouchadam.fyp.algorithm.crossover.binary.Binary;

public class NoteTypeCrossover implements Crossover<NoteType> {

    private final Crossover<Binary> binaryCrossover;

    public NoteTypeCrossover(Crossover<Binary> binaryCrossover) {
        this.binaryCrossover = binaryCrossover;
    }

    @Override
    public NoteType crossover(NoteType parentX, NoteType parentY) {
        Binary crossover = binaryCrossover.crossover(parentX.binary(), parentY.binary());
        if (crossover.toDecimal() >= NoteType.values().length) {
            return parentY;
        }
        return NoteType.from(crossover);
    }
}
