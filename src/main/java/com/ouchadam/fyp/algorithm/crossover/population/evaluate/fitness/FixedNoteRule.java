package com.ouchadam.fyp.algorithm.crossover.population.evaluate.fitness;

import com.ouchadam.fyp.algorithm.ForEach;
import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.FitnessAccumulator;

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

    private final ForEach<Note> evaluate = new ForEach<Note>() {
        @Override
        public void on(Note what) {
            fitnessAccumulator.add(fixedNote.apply(what));
        }
    };

    private static class FixedNote implements FitnessRule<Note> {

        private final static int NOTE_MAX = 127;
        private final static int NOTE_MIN = 0;

        private final int fixedValue;

        private FixedNote(int fixedValue) {
            this.fixedValue = fixedValue;
        }

        @Override
        public FitnessValue apply(Note what) {
            // TODO normalise max and min to 0 - 100
            // TODO use delta to find / adjust the fitness value
            int delta = Math.abs(what.decimal() - fixedValue);
            return new FitnessValue(100 - delta);
        }
    }

}
