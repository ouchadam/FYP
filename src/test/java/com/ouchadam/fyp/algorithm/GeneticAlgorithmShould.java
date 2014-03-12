package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.evaluate.Evaluator;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;
import com.ouchadam.fyp.algorithm.evaluate.rule.FixedKeySignatureRule;
import com.ouchadam.fyp.algorithm.evaluate.rule.NoteDiversityRule;
import com.ouchadam.fyp.algorithm.evaluate.rule.NoteRangeRule;
import com.ouchadam.fyp.algorithm.population.Creator;
import com.ouchadam.fyp.algorithm.population.Evaluation;
import com.ouchadam.fyp.algorithm.population.Population;
import com.ouchadam.fyp.algorithm.population.PopulationCrosser;
import com.ouchadam.fyp.analysis.Key;
import com.ouchadam.fyp.presentation.RuleContainer;
import com.ouchadam.fyp.presentation.RuleName;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import helper.PopulationHelper;
import helper.Printer;
import helper.TestWithMocks;

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
    @Mock List<RuleContainer<Member>> rules;

    @Test
    public void return_when_the_evaulation_output_is_passed() {
        Population pop = PopulationHelper.create();

        Evaluation passedEvaluation = createPassedEvaluation(pop);

        when(evaluator.evaluate(any(Population.class))).thenReturn(passedEvaluation);
        when(creator.create(anyInt())).thenReturn(pop);
        when(mutator.mutate(any(Population.class))).thenReturn(pop);
        when(pruner.selectSeeds(any(Population.class))).thenReturn(pop);
        when(pruner.getBest(any(Population.class))).thenReturn(pop);

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(creator, mutator, crossover, evaluator, pruner, null, halter, new AlgorithmParams(100,100,0, 5, 0, rules));

        Evaluation evaluation = geneticAlgorithm.work();

        assertThat(evaluation).isNotNull();
    }

    @Ignore @Test
    public void full_flow() {
        List<RuleContainer<Member>> ruleList = new ArrayList<RuleContainer<Member>>();
        ruleList.add(new RuleContainer<Member>(FixedKeySignatureRule.newInstance(Key.C), RuleName.KEY, 100));
        ruleList.add(new RuleContainer<Member>(new NoteRangeRule(12), RuleName.RANGE, 100));
        ruleList.add(new RuleContainer<Member>(new NoteDiversityRule(4), RuleName.DIVERSITY, 100));

        AlgorithmParams algorithmParams = new AlgorithmParams(200, 1000, 100, 0, 0, ruleList);
        GeneticAlgorithm geneticAlgorithm = GeneticAlgorithmCreator.newInstance().create(generationCallback, algorithmParams, halter);

        Evaluation output = geneticAlgorithm.work();
        int fitnessValue = output.fitnessValue(0).get();

        Member member = output.population().get(0);
        Printer.printMember(member);

        assertThat(fitnessValue).isGreaterThanOrEqualTo(algorithmParams.getAcceptableFitnessValue());
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
                Printer.printMember(member0);

//                System.out.println("NoteValue range rule: " + new NoteRangeRule(12, 60).apply(member0).getContainer());
//                System.out.println("Key rule: " + new FixedKeySignatureRule(Key.C, new ScaleCreator()).apply(member0).getContainer());

                System.out.println("");
            }
        }
    };

    private int getFrequency(Evaluation evaluation, Member member0) {
        return evaluation.population().frequency(member0);
    }

    private Evaluation createPassedEvaluation(Population pop) {
        Evaluation evaluation = mock(Evaluation.class);
        when(evaluation.fitnessValue(0)).thenReturn(FitnessValue.max());
        when(evaluation.population()).thenReturn(pop);
        when(evaluation.meetsWantedFitness(anyInt())).thenReturn(true);
        return evaluation;
    }

}
