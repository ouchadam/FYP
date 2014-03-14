package com.ouchadam.fyp.algorithm.population;

import java.util.ArrayList;
import java.util.List;

import com.ouchadam.fyp.algorithm.domain.Member;

public class PopulationCreator implements Creator<Population> {

    private final MemberCreator memberCreator;
    private final PopulationCrosser crossover;

    public PopulationCreator(MemberCreator memberCreator, PopulationCrosser crossover) {
        this.memberCreator = memberCreator;
        this.crossover = crossover;
    }

    @Override
    public Population create(int size) {
        List<Member> members = new ArrayList<Member>(size);
        for (int index = 0; index < size; index++) {
            members.add(memberCreator.createRandomMember());
        }
        return crossover.crossover(new Population(members));
    }

}
