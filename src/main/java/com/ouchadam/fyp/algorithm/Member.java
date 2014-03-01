package com.ouchadam.fyp.algorithm;

import java.util.Collections;
import java.util.List;

public class Member {

    public static final int NOTE_CHILD_COUNT = 16;

    private final List<Note> notes;
    private final Each forEach;
    private final All forAll;

    public Member(List<Note> notes) {
        this.notes = Collections.unmodifiableList(notes);
        forEach = new Each();
        forAll = new All();
    }

    public Note note(int index) {
        return notes.get(index);
    }

    public Each forEach() {
        return forEach;
    }

    public All getAll() {
        return forAll;
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

    public int indexOf(Note what) {
        return notes.indexOf(what);
    }

    public class Each {
        public void note(ForEach<Note> forEach) {
            for (Note note : notes) {
                forEach.on(note);
            }
        }
    }

    public class All {
        public List<Note> notes() {
            return notes;
        }
    }

}

