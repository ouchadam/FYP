package algorithm.crossover.population.evaluate;

import algorithm.Member;
import algorithm.crossover.population.evaluate.fitness.FitnessEvaluator;
import algorithm.crossover.population.evaluate.fitness.FixedNoteRule;

public class FitnessFactory {

    public FitnessEvaluator member(Member member) {
        return new MemberEvaluator(new FixedNoteRule(), member);
    }

}
