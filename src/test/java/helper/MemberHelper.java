package helper;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.NoteType;
import com.ouchadam.fyp.algorithm.NoteValue;
import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import com.ouchadam.fyp.presentation.midi.NoteOnFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemberHelper {

    private static final int NOTE_MAX = 127;

    public static Member createRandom() {
        List<NoteValue> arrayList = new ArrayList<NoteValue>();
        List<NoteType> types = new ArrayList<NoteType>();

        for (int index = 0; index < Member.CHILD_COUNT; index++) {
            arrayList.add(new NoteValue(new Binary(String.valueOf(new Random().nextInt(NOTE_MAX)))));
            types.add(NoteType.NOTE);
        }

        return new Member(arrayList, types, new Member.Controller(new NoteOnFilter()));
    }

}
