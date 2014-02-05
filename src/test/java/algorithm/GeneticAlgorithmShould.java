package algorithm;

import algorithm.crossover.population.Evaluation;
import algorithm.crossover.population.Population;
import algorithm.crossover.population.PopulationCreator;
import algorithm.crossover.population.PopulationCrossover;
import algorithm.crossover.population.evaluate.Evaluator;
import algorithm.crossover.population.evaluate.fitness.FitnessValue;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GeneticAlgorithmShould extends TestWithMocks {

    @Mock Evaluator<Population> evaluator;
    @Mock PopulationCreator creator;
    @Mock PopulationMutator mutator;
    @Mock PopulationCrossover crossover;
    @Mock PopulationPruner pruner;

    @Test
    public void return_when_the_evaulation_output_is_passed() {
        Evaluation passedEvaluation = createPassedEvaluation();
        when(evaluator.evaluate(any(Population.class))).thenReturn(passedEvaluation);

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(creator, mutator, crossover, evaluator, pruner);

        geneticAlgorithm.work();
    }

    private Evaluation createPassedEvaluation() {
        Evaluation evaluation = mock(Evaluation.class);
        when(evaluation.fitnessValue()).thenReturn(FitnessValue.max());
        return evaluation;
    }

}
