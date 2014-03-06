package com.ouchadam.fyp.algorithm.population;

import com.ouchadam.fyp.algorithm.IndexManager;
import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.crossover.Crossover;
import com.ouchadam.fyp.algorithm.crossover.PopulationCrossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPopulationCrossover implements PopulationCrossover {

    private final Crossover<Note> crossover;
    private final IndexManager indexManager;
    private final Member.Controller memberController;
    private final Random random;

    public RandomPopulationCrossover(Random random, Crossover<Note> crossover, IndexManager indexManager, Member.Controller memberController) {
        this.random = random;
        this.crossover = crossover;
        this.indexManager = indexManager;
        this.memberController = memberController;
    }

    @Override
    public Population crossover(Population population) {
        List<Integer> indexes = createIndexes(population.size());
        List<Member> newPopulation = new ArrayList<Member>(indexes.size());

        for (int index = 0; index < indexes.size(); index++) {
            int indexPlusOne = getIndexPlusOne(index, population.size());
            newPopulation.add(crossoverMembers(population.get(index), population.get(indexPlusOne)));
            newPopulation.add(crossoverMembers(population.get(indexPlusOne), population.get(index)));
        }
        return new Population(newPopulation);
    }

    private int getIndexPlusOne(int index, int populationSize) {
        return index >= populationSize ? random.nextInt(populationSize - 1) + 1 : index + 1;
    }

    private List<Integer> createIndexes(int populationSize) {
        int crossoverCount = random.nextInt(populationSize - 1) + 1;
        return indexManager.create(crossoverCount, populationSize);
    }

    private Member crossoverMembers(Member memberX, Member memberY) {
        return new Member(crossoverNotes(memberX, memberY), memberController);
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
