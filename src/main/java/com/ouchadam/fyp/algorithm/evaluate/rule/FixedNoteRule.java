package com.ouchadam.fyp.algorithm.evaluate.rule;

import com.ouchadam.fyp.algorithm.domain.Member;
import com.ouchadam.fyp.algorithm.domain.NoteValue;
import com.ouchadam.fyp.algorithm.evaluate.FitnessAccumulator;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;

public class FixedNoteRule implements FitnessRule<Member> {

    private final FixedNote fixedNote;

    public FixedNoteRule(FixedNote fixedNote) {
        this.fixedNote = fixedNote;
    }

    @Override
    public FitnessValue apply(Member what) {
        FitnessAccumulator fitnessAccumulator = FitnessAccumulator.from(what.size());
        for (NoteValue noteValue : what.all().noteValues()) {
            fitnessAccumulator.add(fixedNote.apply(noteValue));
        }
        return fitnessAccumulator.bias(3);
    }

    public static class FixedNote implements FitnessRule<NoteValue> {

        private static final float RESOLUTION = 0.9f;
        private final int fixedValue;

        public FixedNote(int fixedValue) {
            this.fixedValue = fixedValue;
        }

        @Override
        public FitnessValue apply(NoteValue what) {
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
