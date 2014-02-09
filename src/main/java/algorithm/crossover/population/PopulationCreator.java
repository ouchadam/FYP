package algorithm.crossover.population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algorithm.Member;
import algorithm.crossover.binary.Binary;

public class PopulationCreator {

    private final MemberCreator memberCreator;
    private final PopulationCrossover crossover;

    public PopulationCreator(MemberCreator memberCreator, PopulationCrossover crossover) {
        this.memberCreator = memberCreator;
        this.crossover = crossover;
    }

    public Population createPopulation(int size) {
        List<Member> members = new ArrayList<Member>(size);
        for (int index = 0; index < size; index++) {
            members.add(memberCreator.createRandomMember());
        }
        return crossover.crossover(new Population(members));
    }

    public static class MemberCreator {

        private static final int NOTE_MAX = 127;

        private Member createRandomMember() {
            List<Binary> notes = createNotes();
            return new Member(notes);
        }

        private List<Binary> createNotes() {
            List<Binary> notes = new ArrayList<Binary>(Member.CHILD_COUNT);
            for (int index = 0; index < Member.CHILD_COUNT; index++) {
                notes.add(new Binary(Integer.toBinaryString(new Random().nextInt(NOTE_MAX))));
            }
            return notes;
        }

    }

}
