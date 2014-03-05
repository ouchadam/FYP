package com.ouchadam.fyp.algorithm.population.evaluate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ouchadam.fyp.algorithm.ForEach;
import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.population.Evaluation;
import com.ouchadam.fyp.algorithm.population.Population;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.*;
import com.ouchadam.fyp.analysis.Key;

public class PopulationEvaluator implements Evaluator<Population> {

    private final FitnessFactory fitnessFactory;

    public PopulationEvaluator(FitnessFactory fitnessFactory) {
        this.fitnessFactory = fitnessFactory;
    }

    @Override
    public Evaluation evaluate(Population population) {
        List<OrderedPopulation.OrderedMember> valueList = new ArrayList<OrderedPopulation.OrderedMember>(population.size());
        for (Member member : population.all()) {
            FitnessValue value = evaluate(member, NoteRangeRule.newInstance(12, Key.C.value() + 60), FixedKeySignatureRule.newInstance(Key.C));
            valueList.add(new OrderedPopulation.OrderedMember(value, member));
        }
        return new Evaluation(createOrderedPopulation(valueList));
    }

    private OrderedPopulation createOrderedPopulation(List<OrderedPopulation.OrderedMember> valueList) {
        Collections.sort(valueList);
        return new OrderedPopulation(valueList);
    }

    private FitnessValue evaluate(Member member, FitnessRule<Member>... rules) {
        return fitnessFactory.member().evaluate(member, rules);
    }

}
