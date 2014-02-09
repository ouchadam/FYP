package algorithm.crossover.population;

import java.util.ArrayList;
import java.util.List;

import algorithm.Member;
import algorithm.Note;
import algorithm.crossover.Crossover;
import algorithm.crossover.binary.Binary;

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
        population.addToCollection(newPopulation);
        return new Population(newPopulation);
    }

    private Member crossover(Member memberX, Member memberY) {
        // TODO velocity etc...
        List<Note> notes = new ArrayList<Note>(Member.CHILD_COUNT);

        for (int index = 0; index < Member.CHILD_COUNT; index++) {
            notes.add(crossoverNote(noteCrossover, memberX.note(index), memberY.note(index)));
        }
        return new Member(notes);
    }

    private Note crossoverNote(Crossover<Note> crossover, Note noteX, Note noteY) {
        return crossover.crossover(noteX, noteY);
    }

}
