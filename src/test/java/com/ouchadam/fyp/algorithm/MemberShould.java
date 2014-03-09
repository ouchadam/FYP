package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MemberShould {

    @Test (expected = UnsupportedOperationException.class)
    @Ignore
    public void be_unmodifiable() throws Exception {
        List<NoteValue> arrayList = new ArrayList<NoteValue>();
        arrayList.add(new NoteValue(new Binary("01")));
        Member member = new Member(arrayList, new ArrayList<NoteType>(), new Member.Controller());
        List<NoteValue> noteValues = member.all().noteValues();

        noteValues.add(new NoteValue(new Binary("0")));
    }

}
