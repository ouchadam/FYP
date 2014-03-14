package com.ouchadam.fyp.algorithm.mutation;

import com.ouchadam.fyp.algorithm.IndexManager;
import com.ouchadam.fyp.algorithm.domain.Member;
import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import com.ouchadam.fyp.algorithm.domain.NoteType;
import com.ouchadam.fyp.algorithm.domain.NoteValue;

import java.util.List;
import java.util.Random;

public class MemberMutator implements Mutator<Member> {

    private final IndexManager indexManager;
    private final Random random;
    private final Member.Controller memberController;
    private final Mutator<Binary> binaryMutator;

    public MemberMutator(IndexManager indexManager, Random random, Mutator<Binary> binaryMutator, Member.Controller memberController) {
        this.indexManager = indexManager;
        this.random = random;
        this.memberController = memberController;
        this.binaryMutator = binaryMutator;
    }

    @Override
    public Member mutate(Member what) {
        int noteCount = what.size();
        List<Integer> notesToMutate = indexManager.create(noteCount, noteCount);
        List<NoteValue> allValues = what.all().noteValues();
        List<NoteType> allTypes = what.all().noteTypes();

        for (Integer index : notesToMutate) {
            if (random.nextFloat() <= .6f) {
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
        Binary mutation = binaryMutator.mutate(noteType.binary());
        if (mutation.toDecimal() >= NoteType.values().length) {
            return mutateNoteType(noteType);
        }
        return mutation;
    }

}
