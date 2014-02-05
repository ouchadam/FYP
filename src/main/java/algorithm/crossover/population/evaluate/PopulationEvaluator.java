package algorithm.crossover.population.evaluate;

import algorithm.ForEach;
import algorithm.Member;
import algorithm.crossover.population.Evaluation;
import algorithm.crossover.population.Population;
import algorithm.crossover.population.evaluate.fitness.FitnessValue;
import algorithm.crossover.population.evaluate.fitness.FixedNoteRule;

public class PopulationEvaluator implements Evaluator<Population> {

    @Override
    public Evaluation evaluate(Population population) {
        population.forEachMember(member);
        return new Evaluation();
    }

    private final ForEach<Member> member = new ForEach<Member>() {
        @Override
        public void on(Member what) {
            FitnessValue evaluate = new MemberEvaluator(new FixedNoteRule(), what).evaluate();
        }
    };


}
