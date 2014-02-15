package com.ouchadam.fyp.algorithm;

import java.util.ArrayList;
import java.util.List;

class MemberMutator {

    private final IndexManager indexManager;

    private List<Note> notes;
    private BinaryMutator binaryMutator;
    private List<Integer> notesToMutate;
    private Member member;

    MemberMutator(IndexManager indexManager) {
        this.indexManager = indexManager;
    }

    Member mutate(Member what) {
        this.member = what;
        int mutationProbability = 70;
        int noteCount = what.size();
        notesToMutate = indexManager.create(noteCount, noteCount);
        binaryMutator = new BinaryMutator(mutationProbability, indexManager);
        notes = new ArrayList<Note>(noteCount);
        what.forEachNote(mutateNote);
        return new Member(notes);
    }

    private final ForEach<Note> mutateNote = new ForEach<Note>() {
        @Override
        public void on(Note what) {
            int index = member.indexOf(what);
            if (indexManager.isIndex(index, notesToMutate)) {
                notes.add(new Note(binaryMutator.mutate(what.binary())));
            } else {
                notes.add(what);
            }
        }
    };

}
