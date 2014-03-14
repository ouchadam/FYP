package com.ouchadam.fyp.algorithm.evaluate;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.population.Evaluation;
import com.ouchadam.fyp.algorithm.population.Population;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;
import com.ouchadam.fyp.presentation.tab.rule.RuleContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PopulationEvaluator implements Evaluator<Population> {

    private final FitnessFactory fitnessFactory;
    private final List<RuleContainer<Member>> rules;

    public PopulationEvaluator(FitnessFactory fitnessFactory, List<RuleContainer<Member>> rules) {
        this.fitnessFactory = fitnessFactory;
        this.rules = rules;
    }

    @Override
    public Evaluation evaluate(Population population) {
        List<OrderedPopulation.OrderedMember> valueList = new ArrayList<OrderedPopulation.OrderedMember>(population.size());
        for (Member member : population.all()) {
            FitnessValue value = evaluate(member, rules);
            valueList.add(new OrderedPopulation.OrderedMember(value, member));
        }
        return new Evaluation(createOrderedPopulation(valueList));
    }

    private OrderedPopulation createOrderedPopulation(List<OrderedPopulation.OrderedMember> valueList) {
        Collections.sort(valueList);
        return new OrderedPopulation(valueList);
    }

    private FitnessValue evaluate(Member member, List<RuleContainer<Member>> rules) {
        return fitnessFactory.member().evaluate(member, rules);
    }

}
