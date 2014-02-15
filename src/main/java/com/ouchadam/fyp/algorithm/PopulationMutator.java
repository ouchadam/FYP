package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.population.Population;

import java.util.ArrayList;
import java.util.List;

public class PopulationMutator {

    private final IndexManager indexManager;
    private List<Member> members;
    private List<Integer> membersToMutate;
    private Population population;

    public PopulationMutator(IndexManager indexManager) {
        this.indexManager = indexManager;
    }

    public Population mutate(Population population) {
        this.population = population;
        members = new ArrayList<Member>(population.size());
        membersToMutate = indexManager.create(population.size(), population.size());
        population.forEachMember(mutateMember);
        return new Population(members);
    }

    private final ForEach<Member> mutateMember = new ForEach<Member>() {
        @Override
        public void on(Member what) {
            Member newMember = what;
            int index = population.indexOf(what);
            if (indexManager.isIndex(index, membersToMutate)) {
                MemberMutator memberMutator = new MemberMutator(indexManager);
                newMember = memberMutator.mutate(what);
            }
            members.add(newMember);
        }
    };

}
