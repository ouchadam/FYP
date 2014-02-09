package algorithm.crossover.population;

import java.util.ArrayList;
import java.util.List;

import algorithm.Member;
import algorithm.crossover.Crossover;
import algorithm.crossover.binary.Binary;

public class PopulationCrossover {

    private final BinaryCrossover binaryCrossover;

    public PopulationCrossover(BinaryCrossover binaryCrossover) {
        this.binaryCrossover = binaryCrossover;
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
        List<Binary> notes = new ArrayList<Binary>(Member.CHILD_COUNT);
        for (int index = 0; index < Member.CHILD_COUNT; index++) {
            notes.add(crossoverNote(binaryCrossover, memberX.get(index), memberY.get(index)));
        }
        return new Member(notes);
    }

    private Binary crossoverNote(Crossover<Binary> binaryCrossover, Binary binaryX, Binary binaryY) {
        return binaryCrossover.crossover(binaryX, binaryY);
    }

}
