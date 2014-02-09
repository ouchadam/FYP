package algorithm;

import algorithm.crossover.binary.Binary;
import algorithm.crossover.binary.CrossoverFactory;
import algorithm.crossover.population.Evaluation;
import algorithm.crossover.population.Population;
import algorithm.crossover.population.PopulationCreator;
import algorithm.crossover.population.PopulationCrossover;
import algorithm.crossover.population.evaluate.Evaluator;
import algorithm.crossover.population.evaluate.FitnessFactory;
import algorithm.crossover.population.evaluate.PopulationEvaluator;

public class GeneticAlgorithm {

    private final static int INITIAL_POPULATION_SIZE = 2;
    private final static int MAX_POPULATION_SIZE = 10;
    private final static int GENERATION_LIMIT = 1000;
    final static int ACCEPTABLE_FITNESS_VALUE = 80;

    private final PopulationMutator mutator;
    private final PopulationCrossover crossover;
    private final Evaluator<Population> evaluator;
    private final PopulationPruner pruner;
    private final PopulationCreator populationCreator;

    public static GeneticAlgorithm newInstance() {
        CrossoverFactory crossoverFactory = CrossoverFactory.newInstance();
        return new GeneticAlgorithm(
                new PopulationCreator(new PopulationCreator.MemberCreator(), new PopulationCrossover(crossoverFactory.singlePoint().note())),
                new PopulationMutator(),
                new PopulationCrossover(crossoverFactory.uniform().note()),
                new PopulationEvaluator(new FitnessFactory()),
                new PopulationPruner(MAX_POPULATION_SIZE));
    }

    GeneticAlgorithm(PopulationCreator creator, PopulationMutator mutator, PopulationCrossover crossover, Evaluator<Population> evaluator, PopulationPruner pruner) {
        populationCreator = creator;
        this.mutator = mutator;
        this.crossover = crossover;
        this.evaluator = evaluator;
        this.pruner = pruner;
    }

    public Evaluation work() {
        // TODO create initial population
        // TODO loop mutation > select best > crossover
        return loop(createInitialPopulation());
    }

    private Population createInitialPopulation() {
        return populationCreator.createPopulation(INITIAL_POPULATION_SIZE);
    }

    private Evaluation loop(Population population) {
        Evaluation evaluation = null;
        Population result = population;
        int index = 0;
        do {
            evaluation = evaluator.evaluate(mutator.mutate(crossover.crossover(pruner.prune(result))));
            result = evaluation.population();
            System.out.println("fitness value : " + evaluation.fitnessValue().get());
            if (index >= GENERATION_LIMIT) {
                System.out.println("Limit reached, breaking out");
                break;
            }
            index ++;
        } while (evaluation.fitnessValue().get() < ACCEPTABLE_FITNESS_VALUE);
        return evaluation;
    }

    private void printNotes(String stage, Population population) {
        System.out.println("--------------------------------");
        population.forEachMember(printMember);
        System.out.println("--------------------------------");
    }

    private final ForEach<Member> printMember = new ForEach<Member>() {
        @Override
        public void on(Member what) {
            what.forEachNote(printNote);
            System.out.println("");
        }
    };

    private final ForEach<Note> printNote = new ForEach<Note>() {
        @Override
        public void on(Note what) {
            System.out.println(what.decimal());
        }
    };

}
