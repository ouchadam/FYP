package algorithm;

import java.util.Collections;
import java.util.List;

import algorithm.crossover.binary.Binary;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        if (notes != null ? !notes.equals(member.notes) : member.notes != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return notes != null ? notes.hashCode() : 0;
    }
}

