package com.ouchadam.fyp.algorithm.crossover.population;

import java.util.Collections;
import java.util.List;

import com.ouchadam.fyp.algorithm.ForEach;
import com.ouchadam.fyp.algorithm.Member;

public class Population {

    private final List<Member> members;

    public Population(List<Member> members) {
        this.members = Collections.unmodifiableList(members);
    }

    public int size() {
        return members.size();
    }

    public Member get(int index) {
        return members.get(index);
    }

    public void forEachMember(ForEach<Member> forEach) {
        for (Member member : members) {
            forEach.on(member);
        }
    }

    public Population prune(int amount) {
        return new Population(members.subList(0, amount));
    }

    public void addToCollection(List<Member> newPopulation) {
        newPopulation.addAll(members);
    }
}
