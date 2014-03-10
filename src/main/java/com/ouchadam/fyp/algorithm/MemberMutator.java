package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.binary.Binary;

import java.util.List;
import java.util.Random;

class MemberMutator {

    private final IndexManager indexManager;
    private Random random;
    private final Member.Controller memberController;
    private final Mutator<Binary> binaryMutator;

    MemberMutator(IndexManager indexManager, Random random, Mutator<Binary> binaryMutator, Member.Controller memberController) {
        this.indexManager = indexManager;
        this.random = random;
        this.memberController = memberController;
        this.binaryMutator = binaryMutator;
    }

    Member mutate(Member what) {
        int noteCount = what.size();
        List<Integer> notesToMutate = indexManager.create(noteCount, noteCount);
        List<NoteValue> allValues = what.all().noteValues();
        List<NoteType> allTypes = what.all().noteTypes();

        for (Integer index : notesToMutate) {
            if (random.nextFloat() <= 80) {
                allValues.set(index, new NoteValue(mutateValue(allValues.get(index))));
            } else {
                allTypes.set(index, NoteType.from(mutateNoteType(allTypes.get(index))));

            }
        }
        return new Member(allValues, allTypes, memberController);
    }

    private Binary mutateValue(NoteValue noteValue) {
        return binaryMutator.mutate(noteValue.binary());
    }

    private Binary mutateNoteType(NoteType noteType) {
        return binaryMutator.mutate(noteType.binary());
    }

}
