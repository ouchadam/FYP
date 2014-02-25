package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.population.Evaluation;
import com.ouchadam.fyp.algorithm.crossover.population.Population;
import com.ouchadam.fyp.algorithm.crossover.population.PopulationCreator;
import com.ouchadam.fyp.algorithm.crossover.population.PopulationCrossover;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.Evaluator;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.fitness.FitnessValue;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import helper.Printer;
import helper.TestWithMocks;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GeneticAlgorithmShould extends TestWithMocks {

    @Mock Evaluator<Population> evaluator;
    @Mock PopulationCreator creator;
    @Mock PopulationMutator mutator;
    @Mock PopulationCrossover crossover;
    @Mock PopulationPruner pruner;

    @Ignore @Test
    public void return_when_the_evaulation_output_is_passed() {
        Evaluation passedEvaluation = createPassedEvaluation();
        when(evaluator.evaluate(any(Population.class))).thenReturn(passedEvaluation);
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(creator, mutator, crossover, evaluator, pruner, null, halter);

        Evaluation evaluation = geneticAlgorithm.work();
        assertThat(evaluation).isNotNull();
    }

    @Ignore @Test
    public void full_flow() {
        GeneticAlgorithm geneticAlgorithm = GeneticAlgorithm.newInstance(generationCallback, halter);

        Evaluation output = geneticAlgorithm.work();
        int fitnessValue = output.fitnessValue(0).get();

        Member member = output.population().get(0);
        Printer.print(member);

        assertThat(fitnessValue).isGreaterThanOrEqualTo(GeneticAlgorithm.ACCEPTABLE_FITNESS_VALUE);
    }

    private final GeneticAlgorithm.GenerationHalter halter = new GeneticAlgorithm.GenerationHalter() {
        @Override
        public boolean isHalted(Evaluation evaluation, int index) {
            return false;
        }

        @Override
        public void setHalted(boolean halted) {}
    };

    private final GenerationCallback generationCallback = new GenerationCallback() {

        private int fixedIndex = 0;

        @Override
        public void onGeneration(Evaluation evaluation, int index) {
            if (fixedIndex <= (index - 100)) {
                fixedIndex = index;
                System.out.println("-------------- Generation : " + index + " ------------------------");
                Member member0 = evaluation.population().get(0);
                System.out.println("0 :  fitness : " + evaluation.fitnessValue(0).get() + " occurs : " + getFrequency(evaluation, member0));
                Printer.print(member0);
                System.out.println("");
            }
        }
    };

    private int getFrequency(Evaluation evaluation, Member member0) {
        return evaluation.population().frequency(member0);
    }

    private Evaluation createPassedEvaluation() {
        Evaluation evaluation = mock(Evaluation.class);
        when(evaluation.fitnessValue(0)).thenReturn(FitnessValue.max());
        when(evaluation.population()).thenReturn(mock(Population.class));
        return evaluation;
    }

}
