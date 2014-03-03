package com.ouchadam.fyp.algorithm.population;

import com.ouchadam.fyp.ForEachPair;
import com.ouchadam.fyp.algorithm.IndexManager;
import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.crossover.Crossover;
import com.ouchadam.fyp.algorithm.crossover.PopulationCrossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPopulationCrossover implements PopulationCrossover {

    private final List<Member> newPopulation;
    private final Crossover<Note> crossover;
    private final IndexManager indexManager;
    private final Random random;

    private Population population;

    public RandomPopulationCrossover(Random random, Crossover<Note> crossover, IndexManager indexManager) {
        this.random = random;
        this.crossover = crossover;
        this.indexManager = indexManager;
        this.newPopulation = new ArrayList<Member>();
    }

    @Override
    public Population crossover(Population population) {
        this.population = population;
        indexManager.forEach(createIndexes(this.population.size()), forEachPair);
        return new Population(newPopulation);
    }

    private List<Integer> createIndexes(int populationSize) {
        int crossoverCount = random.nextInt(populationSize - 2) + 2;
        return indexManager.create(crossoverCount, populationSize);
    }

    private final ForEachPair<Integer> forEachPair = new ForEachPair<Integer>() {
        @Override
        public void on(Integer x, Integer y) {
            newPopulation.add(crossoverMembers(population.get(x), population.get(y)));
            newPopulation.add(crossoverMembers(population.get(y), population.get(x)));
        }
    };

    private Member crossoverMembers(Member memberX, Member memberY) {
        return new Member(crossoverNotes(memberX, memberY));
    }

    private List<Note> crossoverNotes(Member memberX, Member memberY) {
        List<Note> notes = new ArrayList<Note>(Member.CHILD_COUNT);
        for (int index = 0; index < Member.CHILD_COUNT; index++) {
            notes.add(crossoverNote(crossover, memberX.note(index), memberY.note(index)));
        }
        return notes;
    }

    private Note crossoverNote(Crossover<Note> crossover, Note noteX, Note noteY) {
        return crossover.crossover(noteX, noteY);
    }
}
