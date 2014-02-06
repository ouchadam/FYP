package algorithm.crossover.population.evaluate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import algorithm.ForEach;
import algorithm.Member;
import algorithm.crossover.population.Evaluation;
import algorithm.crossover.population.Population;
import algorithm.crossover.population.evaluate.fitness.FitnessRule;
import algorithm.crossover.population.evaluate.fitness.FitnessValue;
import algorithm.crossover.population.evaluate.fitness.FixedNoteRule;

public class PopulationEvaluator implements Evaluator<Population> {

    private static final int FITTEST_INDEX = 0;
    private final FitnessFactory fitnessFactory;
    private List<OrderedMember> valueList;

    public PopulationEvaluator(FitnessFactory fitnessFactory) {
        this.fitnessFactory = fitnessFactory;
    }

    @Override
    public Evaluation evaluate(Population population) {
        this.valueList = new ArrayList<OrderedMember>(population.size());
        population.forEachMember(member);
        Population orderedPopulation = createOrderedPopulation();
        return new Evaluation(getFittest(), orderedPopulation);
    }

    private FitnessValue getFittest() {
        return valueList.get(FITTEST_INDEX).value;
    }

    private Population createOrderedPopulation() {
        Collections.sort(valueList);
        List<Member> members = new ArrayList<Member>(valueList.size());
        for (OrderedMember orderedMember : valueList) {
            members.add(orderedMember.member);
        }
        return new Population(members);
    }

    private final ForEach<Member> member = new ForEach<Member>() {
        @Override
        public void on(Member member) {
            FitnessValue value = evaluate(member, FixedNoteRule.newInstance(60));
            valueList.add(new OrderedMember(value, member));
        }
    };

    private FitnessValue evaluate(Member member, FitnessRule<Member>... rules) {
        return fitnessFactory.member().evaluate(member, rules);
    }

    private static class OrderedMember implements Comparable<OrderedMember> {

        private final FitnessValue value;
        private final Member member;

        private OrderedMember(FitnessValue value, Member member) {
            this.value = value;
            this.member = member;
        }

        @Override
        public int compareTo(OrderedMember o) {
            return this.value.get() < o.value.get() ? 1 : -1;
        }
    }

}
