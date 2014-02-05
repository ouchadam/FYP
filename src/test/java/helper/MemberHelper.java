package helper;

import algorithm.Member;
import algorithm.crossover.binary.Binary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemberHelper {

    private static final int NOTE_MAX = 127;

    public static Member createRandom() {
        List<Binary> arrayList = new ArrayList<Binary>();
        arrayList.add(new Binary(String.valueOf(new Random().nextInt(NOTE_MAX))));
        return new Member(arrayList);
    }

}
