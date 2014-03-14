package com.ouchadam.fyp.algorithm.population;

import com.ouchadam.fyp.algorithm.domain.Member;
import com.ouchadam.fyp.algorithm.domain.NoteType;
import com.ouchadam.fyp.algorithm.domain.NoteValue;

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
        List<NoteValue> noteValues = createNotes();
        List<NoteType> noteTypes = createNoteTypes();
        return new Member(noteValues, noteTypes, controller);
    }

    private List<NoteValue> createNotes() {
        List<NoteValue> noteValues = new ArrayList<NoteValue>(Member.CHILD_COUNT);
        for (int index = 0; index < Member.CHILD_COUNT; index++) {
            noteValues.add(NoteValue.newInstance(random.nextInt(NoteValue.NOTE_MAX)));
        }
        return noteValues;
    }

    private List<NoteType> createNoteTypes() {
        List<NoteType> noteTypes = new ArrayList<NoteType>(Member.CHILD_COUNT);
        for (int index = 0; index < Member.CHILD_COUNT; index++) {
            noteTypes.add(NoteType.values()[(random.nextInt(NoteType.values().length))]);
        }
        return noteTypes;
    }

}
