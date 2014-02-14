package com.ouchadam.fyp.algorithm;

import java.util.ArrayList;
import java.util.List;

class MemberMutator {

    private final RandomIndexCreator randomIndexCreator;

    private List<Note> notes;
    private BinaryMutator binaryMutator;
    private int[] notesToMutate;

    MemberMutator(RandomIndexCreator randomIndexCreator) {
        this.randomIndexCreator = randomIndexCreator;
    }

    Member mutate(Member what) {
        int mutationProbability = 10; // 25% of note count
        int noteCount = what.size();
        notesToMutate = randomIndexCreator.create(noteCount, noteCount);
        binaryMutator = new BinaryMutator(0, randomIndexCreator);
        notes = new ArrayList<Note>(noteCount);
        what.forEachNote(mutateNote);
        return new Member(notes);
    }

    private final ForEach<Note> mutateNote = new ForEach<Note>() {
        @Override
        public void on(Note what) {
            if (isMutationIndex(notes, what, notesToMutate)) {
                notes.add(new Note(binaryMutator.mutate(what.binary())));
            } else {
                notes.add(what);
            }
        }
    };

    private boolean isMutationIndex(List<Note> notes, Note what, int[] mutationIndexes) {
        int index = notes.indexOf(what);
        for (int mutationIndex : mutationIndexes) {
            if (mutationIndex == index) {
                return true;
            }
        }
        return false;
    }

}
