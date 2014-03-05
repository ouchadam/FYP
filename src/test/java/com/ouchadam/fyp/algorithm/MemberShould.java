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
        List<Note> arrayList = new ArrayList<Note>();
        arrayList.add(new Note(new Binary("01")));
        Member member = new Member(arrayList, new Member.Controller());
        List<Note> notes = member.all().notes();

        notes.add(new Note(new Binary("0")));
    }

}
