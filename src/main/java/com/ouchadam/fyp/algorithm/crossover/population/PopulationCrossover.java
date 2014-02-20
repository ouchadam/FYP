package com.ouchadam.fyp.algorithm.crossover.population;

import com.ouchadam.fyp.ForEachPair;
import com.ouchadam.fyp.algorithm.*;
import com.ouchadam.fyp.algorithm.crossover.Crossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopulationCrossover {

    private final IndexManager indexManager;
    private final Crossover<Note> noteCrossover;

    public PopulationCrossover(IndexManager indexManager, Crossover<Note> noteCrossover) {
        this.indexManager = indexManager;
        this.noteCrossover = noteCrossover;
    }

    public Population crossover(Population population) {
        return new Population(crossoverPopulation(population.shuffle()));
    }

    private List<Member> crossoverPopulation(Population population) {
        PopulationPairCrossover forEach = new PopulationPairCrossover(noteCrossover, indexManager);
        return forEach.crossover(population);
    }

    private static class PopulationPairCrossover {

        private final List<Member> newPopulation;

        private final Crossover<Note> crossover;
        private final IndexManager indexManager;
        private final Random random = new Random();

        private Population population;

        private PopulationPairCrossover(Crossover<Note> crossover, IndexManager indexManager) {
            this.crossover = crossover;
            this.indexManager = indexManager;
            this.newPopulation = new ArrayList<Member>();
        }

        public List<Member> crossover(Population population) {
            this.population = population;
            indexManager.forEach(createIndexes(population.size()), forEachPair);
            return newPopulation;
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
            List<Note> notes = crossoverNotes(memberX, memberY);
            return new Member(notes);
        }

        private List<Note> crossoverNotes(Member memberX, Member memberY) {
            List<Note> notes = new ArrayList<Note>(Member.NOTE_CHILD_COUNT);
            for (int index = 0; index < Member.NOTE_CHILD_COUNT; index++) {
                notes.add(crossoverNote(crossover, memberX.note(index), memberY.note(index)));
            }
            return notes;
        }

        private Note crossoverNote(Crossover<Note> crossover, Note noteX, Note noteY) {
            return crossover.crossover(noteX, noteY);
        }
    }

}
