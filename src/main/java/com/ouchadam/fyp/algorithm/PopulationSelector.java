package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.domain.Member;
import com.ouchadam.fyp.algorithm.population.Population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class PopulationSelector {

    private static final double FIVE_PERCENT_COEFF = 0.05;
    private final Random random;
    private final Tournament tournament;

    PopulationSelector(Random random, Tournament tournament) {
        this.random = random;
        this.tournament = tournament;
    }

    public Population selectSeeds(Population population) {
        if (population.size() == 0) {
            throw new RuntimeException("Population is empty??");
        }

        int newPopulationSize = getPopulationSize(population);
        List<Member> memberList = new ArrayList<Member>(newPopulationSize);
        Population shuffledPopulation = population.shuffle();
        for (int index = 0; index < newPopulationSize; index++) {
            Member memberOne = shuffledPopulation.get(random.nextInt(shuffledPopulation.size()));
            Member memberTwo = shuffledPopulation.get(random.nextInt(shuffledPopulation.size()));
            memberList.add(tournament.tournament(population, memberOne, memberTwo));
        }
        return new Population(memberList);
    }

    private int getPopulationSize(Population population) {
        if (population.size() == 1) {
            return 1;
        }
        return random.nextInt(population.size() - 1) + 1;
    }

    public Population getBest(Population generation) {
        return generation.getSubPopulation(0, getFivePercentOfSize(generation));
    }

    private int getFivePercentOfSize(Population generation) {
        return (int) Math.floor((float) generation.size() * FIVE_PERCENT_COEFF);
    }

}
