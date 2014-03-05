package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.population.*;
import com.ouchadam.fyp.algorithm.population.evaluate.Evaluator;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessRule;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessValue;

import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FixedKeySignatureRule;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.NoteRangeRule;
import com.ouchadam.fyp.analysis.Key;
import helper.PopulationHelper;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import helper.Printer;
import helper.TestWithMocks;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GeneticAlgorithmShould extends TestWithMocks {

    @Mock Evaluator<Population> evaluator;
    @Mock Creator<Population> creator;
    @Mock Mutator<Population> mutator;
    @Mock PopulationCrosser crossover;
    @Mock PopulationSelector pruner;
    @Mock List<FitnessRule<Member>> rules;

    @Test
    public void return_when_the_evaulation_output_is_passed() {
        Population pop = PopulationHelper.create();

        Evaluation passedEvaluation = createPassedEvaluation();

        when(evaluator.evaluate(any(Population.class))).thenReturn(passedEvaluation);
        when(creator.create(anyInt())).thenReturn(pop);
        when(mutator.mutate(any(Population.class))).thenReturn(pop);
        when(pruner.selectSeeds(any(Population.class))).thenReturn(pop);

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(creator, mutator, crossover, evaluator, pruner, null, halter, new AlgorithmParams(100,100,100, 5, 0, rules));

        Evaluation evaluation = geneticAlgorithm.work();

        assertThat(evaluation).isNotNull();
    }

    @Ignore @Test
    public void full_flow() {
        List<FitnessRule<Member>> ruleList = new ArrayList<FitnessRule<Member>>();
        ruleList.add(FixedKeySignatureRule.newInstance(Key.C));
        ruleList.add(NoteRangeRule.newInstance(12, Key.C.value() + 60));

        AlgorithmParams algorithmParams = new AlgorithmParams(200, 10000, 100, 5, 0, ruleList);
        GeneticAlgorithm geneticAlgorithm = GeneticAlgorithm.newInstance(generationCallback, algorithmParams, halter);

        Evaluation output = geneticAlgorithm.work();
        int fitnessValue = output.fitnessValue(0).get();

        Member member = output.population().get(0);
        Printer.print(member);

        assertThat(fitnessValue).isGreaterThanOrEqualTo(algorithmParams.acceptableFitnessValue);
    }

    private final GenerationHalter halter = new GenerationHalter() {
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
            if (fixedIndex <= (index - 40)) {
                fixedIndex = index;
                System.out.println("-------------- Generation : " + index + " ------------------------");
                Member member0 = evaluation.population().get(0);
                System.out.println("0 :  fitness total: " + evaluation.fitnessValue(0).get() + " occurs : " + getFrequency(evaluation, member0));
                Printer.print(member0);

//                System.out.println("Note range rule: " + new NoteRangeRule(12, 60).apply(member0).get());
//                System.out.println("Key rule: " + new FixedKeySignatureRule(Key.C, new ScaleCreator()).apply(member0).get());

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
