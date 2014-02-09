package algorithm;

import algorithm.crossover.binary.Binary;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MemberShould {

    @Test (expected = UnsupportedOperationException.class)
    public void be_unmodifiable() throws Exception {
        List<Note> arrayList = new ArrayList<Note>();
        arrayList.add(new Note(new Binary("01")));
        Member member = new Member(arrayList);
        List<Note> notes = member.getNotes();

        notes.add(new Note(new Binary("0")));
    }

}
