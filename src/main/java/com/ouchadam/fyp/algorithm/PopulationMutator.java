package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.population.Population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopulationMutator implements Mutator<Population> {

    private final IndexManager indexManager;
    private final Random random;
    private final MemberMutator memberMutator;

    public PopulationMutator(IndexManager indexManager, Random random, MemberMutator memberMutator) {
        this.indexManager = indexManager;
        this.random = random;
        this.memberMutator = memberMutator;
    }

    @Override
    public Population mutate(Population population) {
        if (population.size() > 2) {
            List<Integer> membersToMutate = indexManager.create(random.nextInt(get50PercentOfPopulationSize(population) + 1), population.size());
            List<Member> all = population.all();
            for (Integer index : membersToMutate) {
                all.set(index, memberMutator.mutate(all.get(index)));
            }
            return new Population(all);
        }
        return population;
    }

    private int get50PercentOfPopulationSize(Population population) {
        return (int) Math.ceil(population.size() * 0.5f);
    }

}
