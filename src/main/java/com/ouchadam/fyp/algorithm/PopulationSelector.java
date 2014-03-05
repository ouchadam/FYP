package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.population.Population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class PopulationSelector {

    private final Type type;
    private final Random random;

    public enum Type {
        ELITISM, TOURNAMENT
    }

    PopulationSelector(Type type, Random random) {
        this.type = type;
        this.random = random;
    }

    public Population selectSeeds(Population population) {
        switch (type) {
            case ELITISM:
                break;
            case TOURNAMENT:
                break;
        }

        List<Member> memberList = new ArrayList<Member>(1000);
        Population shuffledPopulation = population.shuffle();
        for (int index = 0; index < 1000; index++) {
            Member memberOne = shuffledPopulation.get(random.nextInt(shuffledPopulation.size()));
            Member memberTwo = shuffledPopulation.get(random.nextInt(shuffledPopulation.size()));
            memberList.add(tournament(population, memberOne, memberTwo));
        }
        return new Population(memberList);
    }

    private Member tournament(Population population, Member memberOne, Member memberTwo) {
        return population.indexOf(memberOne) < population.indexOf(memberTwo) ? memberOne : memberTwo;
    }

    private Population getTop20Percent(Population population) {
        Population top20Percent = getBest(population);
        return Population.fromSubPopulation(top20Percent, removeDuplicates(population.getSubPopulation(top20Percent.size(), population.size())));
    }

    public Population getBest(Population generation) {
        return generation.getSubPopulation(0, get20PercentOfSize(generation));
    }

    private int get20PercentOfSize(Population generation) {
        return (int) Math.floor((float) generation.size() * 0.1);
    }

    private Population removeDuplicates(Population population) {
        Population sub100 = population.getSubPopulation(get20PercentOfSize(population) + 1, population.size());
        return sub100.removeDuplicates();
    }
}
