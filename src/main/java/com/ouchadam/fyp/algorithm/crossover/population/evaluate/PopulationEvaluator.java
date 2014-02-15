package com.ouchadam.fyp.algorithm.crossover.population.evaluate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ouchadam.fyp.algorithm.ForEach;
import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.crossover.population.Evaluation;
import com.ouchadam.fyp.algorithm.crossover.population.Population;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.fitness.FitnessRule;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.fitness.FitnessValue;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.fitness.FixedNoteRule;

public class PopulationEvaluator implements Evaluator<Population> {

    private final FitnessFactory fitnessFactory;
    private List<OrderedPopulation.OrderedMember> valueList;

    public PopulationEvaluator(FitnessFactory fitnessFactory) {
        this.fitnessFactory = fitnessFactory;
    }

    @Override
    public Evaluation evaluate(Population population) {
        this.valueList = new ArrayList<OrderedPopulation.OrderedMember>(population.size());
        population.forEachMember(member);
        return new Evaluation(createOrderedPopulation());
    }

    private OrderedPopulation createOrderedPopulation() {
        Collections.sort(valueList);
        return new OrderedPopulation(valueList);
    }

    private final ForEach<Member> member = new ForEach<Member>() {
        @Override
        public void on(Member member) {
            FitnessValue value = evaluate(member, FixedNoteRule.newInstance(60));
            valueList.add(new OrderedPopulation.OrderedMember(value, member));
        }
    };

    private FitnessValue evaluate(Member member, FitnessRule<Member>... rules) {
        return fitnessFactory.member().evaluate(member, rules);
    }


}
