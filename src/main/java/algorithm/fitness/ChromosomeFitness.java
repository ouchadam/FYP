package algorithm.fitness;

import algorithm.population.ChromosomeManager;

public class ChromosomeFitness implements FitnessEvaluator<ChromosomeManager> {

    @Override
    public FitnessValue evaluate(ChromosomeManager what) {
        what.getFitness();
        return null;
    }
}
