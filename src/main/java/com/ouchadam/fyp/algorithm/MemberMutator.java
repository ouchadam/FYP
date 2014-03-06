package com.ouchadam.fyp.algorithm;

import java.util.List;
import java.util.Random;

class MemberMutator {

    private final IndexManager indexManager;
    private final Member.Controller memberController;
    private final BinaryMutator binaryMutator;

    MemberMutator(IndexManager indexManager, Random random, int mutationProbability, Member.Controller memberController) {
        this.indexManager = indexManager;
        this.memberController = memberController;
        this.binaryMutator = new BinaryMutator(mutationProbability, indexManager, random);
    }

    Member mutate(Member what) {
        int noteCount = what.size();
        List<Integer> notesToMutate = indexManager.create(noteCount, noteCount);
        List<Note> all = what.all().notes();
        for (Integer index : notesToMutate) {
            all.set(index, new Note(binaryMutator.mutate(all.get(index).binary())));
        }
        return new Member(all, memberController);
    }

}
