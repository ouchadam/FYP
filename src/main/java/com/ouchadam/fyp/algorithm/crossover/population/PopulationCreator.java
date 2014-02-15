package com.ouchadam.fyp.algorithm.crossover.population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.crossover.binary.Binary;

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

        private Member createRandomMember() {
            List<Note> notes = createNotes();
            return new Member(notes);
        }

        private List<Note> createNotes() {
            List<Note> notes = new ArrayList<Note>(Member.NOTE_CHILD_COUNT);
            for (int index = 0; index < Member.NOTE_CHILD_COUNT; index++) {
                notes.add(Note.newInstance(new Random().nextInt(Note.NOTE_MAX)));
            }
            return notes;
        }

    }

}
