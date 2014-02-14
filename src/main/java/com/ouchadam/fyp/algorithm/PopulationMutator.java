package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.population.Population;

import java.util.ArrayList;
import java.util.List;

public class PopulationMutator {

    private final RandomIndexCreator randomIndexCreator;
    private List<Member> members;
    private int[] membersToMutate;

    public PopulationMutator(RandomIndexCreator randomIndexCreator) {
        this.randomIndexCreator = randomIndexCreator;
    }

    public Population mutate(Population population) {
        members = new ArrayList<Member>(population.size());
        membersToMutate = randomIndexCreator.create(population.size(), population.size());
        population.forEachMember(mutateMember);
        return population;
    }

    private final ForEach<Member> mutateMember = new ForEach<Member>() {
        @Override
        public void on(Member what) {
            Member newMember = what;
            if (isMutationIndex(members, what, membersToMutate)) {
                MemberMutator memberMutator = new MemberMutator(randomIndexCreator);
                newMember = memberMutator.mutate(what);
            }
            members.add(newMember);
        }
    };

    private boolean isMutationIndex(List<Member> members, Member what, int[] mutationIndexes) {
        int index = members.indexOf(what);
        for (int mutationIndex : mutationIndexes) {
            if (mutationIndex == index) {
                return true;
            }
        }
        return false;
    }

}
