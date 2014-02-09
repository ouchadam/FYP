package algorithm.crossover.population.evaluate.fitness;

import algorithm.ForEach;
import algorithm.Member;
import algorithm.crossover.binary.Binary;
import algorithm.crossover.population.evaluate.FitnessAccumulator;

public class FixedNoteRule implements FitnessRule<Member> {

    private final FixedNote fixedNote;
    private FitnessAccumulator fitnessAccumulator;

    public static FixedNoteRule newInstance(int fixedValue) {
        return new FixedNoteRule(new FixedNote(fixedValue));
    }

    public FixedNoteRule(FixedNote fixedNote) {
        this.fixedNote = fixedNote;
    }

    @Override
    public FitnessValue apply(Member what) {
        fitnessAccumulator = FitnessAccumulator.from(what.size());
        what.forEachNote(evaluate);
        return fitnessAccumulator.average();
    }

    private final ForEach<Binary> evaluate = new ForEach<Binary>() {
        @Override
        public void on(Binary what) {
            fitnessAccumulator.add(fixedNote.apply(what));
        }
    };

    private static class FixedNote implements FitnessRule<Binary> {

        private final static int NOTE_MAX = 127;
        private final static int NOTE_MIN = 0;

        private final int fixedValue;

        private FixedNote(int fixedValue) {
            this.fixedValue = fixedValue;
        }

        @Override
        public FitnessValue apply(Binary what) {
            // TODO normalise max and min to 0 - 100
            // TODO use delta to find / adjust the fitness value
            int delta = Math.abs(what.toDecimal() - fixedValue);
            return new FitnessValue(100 - delta);

//            if (what.toDecimal() == fixedValue) {
//                return FitnessValue.max();
//            }
//            return FitnessValue.min();
        }
    }

}
