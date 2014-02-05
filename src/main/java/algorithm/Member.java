package algorithm;

import algorithm.crossover.binary.Binary;

import java.util.Collections;
import java.util.List;

public class Member {

    public static final int CHILD_COUNT = 4;

    private final List<Binary> notes;

    public Member(List<Binary> notes) {
        this.notes = Collections.unmodifiableList(notes);
    }

    public Binary get(int index) {
        return notes.get(index);
    }

    List<Binary> getNotes() {
        return notes;
    }

    public void forEachNote(ForEach<Binary> forEach) {
        for (Binary note : notes) {
            forEach.on(note);
        }
    }

    public int size() {
        return notes.size();
    }
}
