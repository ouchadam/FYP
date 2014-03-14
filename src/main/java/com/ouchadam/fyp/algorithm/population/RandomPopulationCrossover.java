package com.ouchadam.fyp.algorithm.population;

import com.ouchadam.fyp.algorithm.IndexManager;
import com.ouchadam.fyp.algorithm.domain.Member;
import com.ouchadam.fyp.algorithm.domain.NoteType;
import com.ouchadam.fyp.algorithm.domain.NoteValue;
import com.ouchadam.fyp.algorithm.crossover.Crossover;
import com.ouchadam.fyp.algorithm.crossover.PopulationCrossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPopulationCrossover implements PopulationCrossover {

    private final Crossover<NoteValue> noteValueCrossover;
    private final Crossover<NoteType> noteTypeCrossover;
    private final IndexManager indexManager;
    private final Member.Controller memberController;
    private final Random random;

    public RandomPopulationCrossover(Random random, Crossover<NoteValue> noteValueCrossover, Crossover<NoteType> noteTypeCrossover, IndexManager indexManager, Member.Controller memberController) {
        this.random = random;
        this.noteValueCrossover = noteValueCrossover;
        this.noteTypeCrossover = noteTypeCrossover;
        this.indexManager = indexManager;
        this.memberController = memberController;
    }

    @Override
    public Population crossover(Population population) {
        List<Integer> indexes = createIndexes(population.size());
        List<Member> newPopulation = new ArrayList<Member>(indexes.size());
        for (Integer index : indexes) {
            int indexPlusOne = getIndexPlusOne(index, population.size());
            newPopulation.add(crossoverMembers(population.get(index), population.get(indexPlusOne)));
            newPopulation.add(crossoverMembers(population.get(indexPlusOne), population.get(index)));
        }
        return newPopulation.size() == 0 ? population : new Population(newPopulation);
    }

    private int getIndexPlusOne(int index, int populationSize) {
        return index >= populationSize ? random.nextInt(populationSize - 1) + 1 : index + 1;
    }

    private List<Integer> createIndexes(int populationSize) {
        if (populationSize == 0) {
            throw new RuntimeException("Population size is too low: " + populationSize + " bailing out");
        }
        if (populationSize == 1) {
            return new ArrayList<Integer>(0);
        } else {
            int crossoverCount = random.nextInt(populationSize - 1) + 1;
            return indexManager.create(crossoverCount, populationSize - 1);
        }
    }

    private Member crossoverMembers(Member memberX, Member memberY) {
        NoteCrossoverContainer container = crossoverNotes(memberX, memberY);
        return new Member(container.noteValues, container.noteTypes, memberController);
    }

    private NoteCrossoverContainer crossoverNotes(Member memberX, Member memberY) {
        NoteCrossoverContainer container = new NoteCrossoverContainer();
        for (int index = 0; index < Member.CHILD_COUNT; index++) {
            container.noteValues.add(crossoverValue(noteValueCrossover, memberX.note(index), memberY.note(index)));
            container.noteTypes.add(crossoverType(noteTypeCrossover, memberX.type(index), memberY.type(index)));
        }
        return container;
    }

    private NoteValue crossoverValue(Crossover<NoteValue> crossover, NoteValue noteValueX, NoteValue noteValueY) {
        return crossover.crossover(noteValueX, noteValueY);
    }

    private NoteType crossoverType(Crossover<NoteType> crossover, NoteType typeX, NoteType typeY) {
        return crossover.crossover(typeX, typeY);
    }

    private static class NoteCrossoverContainer {

        private final List<NoteValue> noteValues;
        private final List<NoteType> noteTypes;

        private NoteCrossoverContainer() {
            noteValues = new ArrayList<NoteValue>(Member.CHILD_COUNT);
            noteTypes = new ArrayList<NoteType>(Member.CHILD_COUNT);
        }
    }
}
