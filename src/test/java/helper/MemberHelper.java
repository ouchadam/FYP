package helper;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.crossover.binary.Binary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemberHelper {

    private static final int NOTE_MAX = 127;

    public static Member createRandom() {
        List<Note> arrayList = new ArrayList<Note>();
        arrayList.add(new Note(new Binary(String.valueOf(new Random().nextInt(NOTE_MAX)))));
        return new Member(arrayList, new Member.Controller());
    }

}
