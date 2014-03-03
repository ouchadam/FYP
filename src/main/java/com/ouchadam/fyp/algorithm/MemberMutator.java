package com.ouchadam.fyp.algorithm;

import java.util.ArrayList;
import java.util.List;

class MemberMutator {

    private final IndexManager indexManager;

    private BinaryMutator binaryMutator;

    MemberMutator(IndexManager indexManager, int mutationProbability) {
        this.indexManager = indexManager;
        this.binaryMutator = new BinaryMutator(mutationProbability, indexManager);
    }

    Member mutate(Member what) {
        int noteCount = what.size();
        List<Integer> notesToMutate = indexManager.create(noteCount, noteCount);
        List<Note> all = what.all().notes();
        for (Integer index : notesToMutate) {
            all.set(index, new Note(binaryMutator.mutate(all.get(index).binary())));
        }
        return new Member(all);
    }

}
