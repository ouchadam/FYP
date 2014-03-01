package com.ouchadam.fyp.algorithm.population.evaluate.fitness;

import com.ouchadam.fyp.algorithm.ForEach;
import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.population.evaluate.FitnessAccumulator;

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
        what.forEach().note(evaluate);
        return fitnessAccumulator.average();
    }

    private final ForEach<Note> evaluate = new ForEach<Note>() {
        @Override
        public void on(Note what) {
            fitnessAccumulator.add(fixedNote.apply(what));
        }
    };

    private static class FixedNote implements FitnessRule<Note> {

        private static final float RESOLUTION = 0.9f;
        private final int fixedValue;

        private FixedNote(int fixedValue) {
            this.fixedValue = fixedValue;
        }

        @Override
        public FitnessValue apply(Note what) {
            int delta = Math.abs(what.decimal() - fixedValue);
            int penalty = createRelativePenalty(delta);
            int value = normalise_0_to_100(100 - (delta + penalty));
            return new FitnessValue(value);
        }

        private int createRelativePenalty(float delta) {
            float multiplier = (delta * RESOLUTION);
            return (int) Math.ceil(multiplier * delta);
        }

        private int normalise_0_to_100(int value) {
            return value < 0 ? 0 : value > 100 ? 100 : value;
        }
    }

}
