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

        private final int fixedValue;

        private FixedNote(int fixedValue) {
            this.fixedValue = fixedValue;
        }

        @Override
        public FitnessValue apply(Note what) {

//            int worth = 20; // TODO hitting the fixed value is worth atleast 20 more than any other
            int delta = Math.abs(what.decimal() - fixedValue);

            float multiplier = ((float)delta % 5f);

            int penalty = multiplier;

//            int foo = (delta - worth);


            // TODO normalise max and min to 0 - 100
            // TODO use delta to find / adjust the fitness value
            return new FitnessValue(foo < 0 ? 0 : foo);
        }
    }

}
