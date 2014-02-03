package algorithm;

import algorithm.crossover.binary.Binary;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MemberShould {

    @Test (expected = UnsupportedOperationException.class)
    public void be_unmodifiable() throws Exception {
        List<Binary> arrayList = new ArrayList<Binary>();
        arrayList.add(new Binary("01"));
        Member member = new Member(arrayList);
        List<Binary> notes = member.getNotes();

        notes.add(new Binary("0"));
    }

}
