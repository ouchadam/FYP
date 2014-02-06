package algorithm.crossover.population;

import java.util.ArrayList;
import java.util.List;

import algorithm.Member;
import algorithm.crossover.Crossover;
import algorithm.crossover.binary.Binary;
import algorithm.crossover.binary.BinaryPadder;
import algorithm.crossover.binary.BinarySinglePointCrossover;

public class PopulationCrossover {

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
        BinaryCrossover binaryCrossover = new BinaryCrossover(new BinarySinglePointCrossover(1), new BinaryPadder());
        List<Binary> notes = new ArrayList<Binary>(Member.CHILD_COUNT);
        for (int index = 0; index < Member.CHILD_COUNT; index++) {
            notes.add(crossoverNote(binaryCrossover, memberX.get(index), memberY.get(index)));
        }
        return new Member(notes);
    }

    private Binary crossoverNote(Crossover<Binary> binaryCrossover, Binary binaryX, Binary binaryY) {
        return binaryCrossover.crossover(binaryX, binaryY);
    }

    private static class BinaryCrossover implements Crossover<Binary> {

        private final Crossover<Binary> crosser;
        private final BinaryPadder binaryPadder;

        private BinaryCrossover(Crossover<Binary> crosser, BinaryPadder binaryPadder) {
            this.crosser = crosser;
            this.binaryPadder = binaryPadder;
        }

        @Override
        public Binary crossover(Binary parentX, Binary parentY) {
            BinaryPadder.MatchedLengthBinary matchedBinaries = binaryPadder.pad(parentX, parentY);
            return crosser.crossover(matchedBinaries.binaryX, matchedBinaries.binaryY);
        }
    }

}
