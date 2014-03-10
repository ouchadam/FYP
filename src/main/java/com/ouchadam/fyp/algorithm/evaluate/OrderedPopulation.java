package com.ouchadam.fyp.algorithm.evaluate;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.population.Population;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;

import java.util.ArrayList;
import java.util.List;

public class OrderedPopulation {

    private final List<OrderedMember> valueList;

    public OrderedPopulation(List<OrderedMember> valueList) {
        this.valueList = valueList;
    }

    public FitnessValue getFitness(int index) {
        return valueList.get(index).value;
    }

    public Population asPopulation() {
        List<Member> members = new ArrayList<Member>(valueList.size());
        for (OrderedMember orderedMember : valueList) {
            members.add(orderedMember.member);
        }
        return new Population(members);
    }

    public static class OrderedMember implements Comparable<OrderedMember> {

        private final FitnessValue value;
        private final Member member;

        OrderedMember(FitnessValue value, Member member) {
            this.value = value;
            this.member = member;
        }

        @Override
        public int compareTo(OrderedMember o) {
            int value1 = value.get();
            int value2 = o.value.get();
            return value1 == value2 ? 0 : value1 < value2 ? 1 : -1;
        }

    }
}
