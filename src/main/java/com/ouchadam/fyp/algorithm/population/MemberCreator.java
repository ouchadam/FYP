package com.ouchadam.fyp.algorithm.population;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemberCreator {

    private final Member.Controller controller;
    private final Random random;

    public MemberCreator(Member.Controller controller, Random random) {
        this.controller = controller;
        this.random = random;
    }

    public Member createRandomMember() {
        List<Note> notes = createNotes();
        return new Member(notes, controller);
    }

    private List<Note> createNotes() {
        List<Note> notes = new ArrayList<Note>(Member.CHILD_COUNT);
        for (int index = 0; index < Member.CHILD_COUNT; index++) {
            notes.add(Note.newInstance(random.nextInt(Note.NOTE_MAX)));
        }
        return notes;
    }

}
