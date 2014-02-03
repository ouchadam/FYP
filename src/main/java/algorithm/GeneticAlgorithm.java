package algorithm;

import algorithm.crossover.population.*;

public class GeneticAlgorithm {

    private final static int INITIAL_POPULATION_SIZE = 2;
    private final static int MAX_POPULATION_SIZE = 10;

    private final static int ACCEPTABLE_FITNESS_VALUE = 10;

    private PopulationMutator mutator;
    private PopulationCrossover crossover;
    private PopulationEvaluator evaluator;
    private PopulationPruner pruner;

    public void entry() {
        // TODO create initial population
        PopulationCreator populationCreator = new PopulationCreator(new PopulationCreator.MemberCreator());
        Population population = populationCreator.createPopulation(INITIAL_POPULATION_SIZE);

        mutator = new PopulationMutator();
        crossover = new PopulationCrossover();
        evaluator = new PopulationEvaluator();
        pruner = new PopulationPruner(MAX_POPULATION_SIZE);


        Population finalPopulation = recur(population);

        // TODO loop mutation > select best > crossover

    }


    private Population recur(Population population) {
        // TODO create new population generation via breeding
        Evaluation evaluation = evaluator.evaluate(mutator.mutate(crossover.crossover(pruner.prune(population))));
        return evaluation.fitnessValue() >= ACCEPTABLE_FITNESS_VALUE ? evaluation.population() : recur(evaluation.population());

    }

    private static class PopulationPruner {

        private final int maxPopulationSize;

        private PopulationPruner(int maxPopulationSize) {
            this.maxPopulationSize = maxPopulationSize;
        }

        public Population prune(Population population) {
            if (population.size() > maxPopulationSize) {
                // TODO remove worst - should be the last positions as the evaluation process should reorder the members
                return population.prune(population);
            }
            return population;
        }
    }

}
