package com.ouchadam.fyp.algorithm.crossover.population;

import java.util.ArrayList;
import java.util.List;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.crossover.Crossover;

public class PopulationCrossover {

    private final Crossover<Note> noteCrossover;

    public PopulationCrossover(Crossover<Note> noteCrossover) {
        this.noteCrossover = noteCrossover;
    }

    public Population crossover(Population population) {
        List<Member> newPopulation = new ArrayList<Member>(population.size() / 2);
        for (int index = 0; index < population.size(); index += 2) {
            if (population.size() > index + 1) {
                newPopulation.add(crossover(population.get(index), population.get(index + 1)));
            } else {
                newPopulation.add(crossover(population.get(index), population.get(0)));
            }
        }
        return new Population(newPopulation);
    }

    private Member crossover(Member memberX, Member memberY) {
        List<Note> notes = crossoverNotes(memberX, memberY);
        return new Member(notes);
    }

    private List<Note> crossoverNotes(Member memberX, Member memberY) {
        List<Note> notes = new ArrayList<Note>(Member.NOTE_CHILD_COUNT);
        for (int index = 0; index < Member.NOTE_CHILD_COUNT; index++) {
            notes.add(crossoverNote(noteCrossover, memberX.note(index), memberY.note(index)));
        }
        return notes;
    }

    private Note crossoverNote(Crossover<Note> crossover, Note noteX, Note noteY) {
        return crossover.crossover(noteX, noteY);
    }

}
