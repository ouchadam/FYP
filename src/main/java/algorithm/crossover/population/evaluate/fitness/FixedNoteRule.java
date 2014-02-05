package algorithm.crossover.population.evaluate.fitness;

import algorithm.crossover.binary.Binary;

public class FixedNoteRule implements FitnessRule<Binary> {

    private final static int NOTE_MAX = 127;
    private final static int NOTE_MIN = 0;

    private final static int FIXED_VALUE = 60;

    @Override
    public FitnessValue apply(Binary what) {
        // TODO normalise max and min to 0 - 100
        // TODO use delta to find adjust the fitness value

        if (what.toDecimal() == FIXED_VALUE) {
            return FitnessValue.max();
        }
        return FitnessValue.min();
    }
}
