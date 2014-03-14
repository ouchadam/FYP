package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.presentation.midi.NoteOnFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {

    public static final int CHILD_COUNT = 16;

    private final List<NoteValue> noteValues;
    private final List<NoteType> noteTypes;
    private final Controller controller;

    public Member(List<NoteValue> noteValues, List<NoteType> noteTypes, Controller controller) {
        this.noteTypes = Collections.unmodifiableList(noteTypes);
        this.noteValues = Collections.unmodifiableList(noteValues);
        this.controller = controller;
    }

    public NoteValue note(int index) {
        return noteValues.get(index);
    }

    public NoteType type(int index) {
        return noteTypes.get(index);
    }

    public int size() {
        return noteValues.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        if (noteValues != null ? !noteValues.equals(member.noteValues) : member.noteValues != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return noteValues != null ? noteValues.hashCode() : 0;
    }

    public Controller.All all() {
        return controller.all(this);
    }

    public Controller.Only only() {
        return controller.only(this);
    }

    public static class Controller {

        private final NoteOnFilter noteOnFilter;

        public Controller(NoteOnFilter noteOnFilter) {
            this.noteOnFilter = noteOnFilter;
        }

        public All all(Member member) {
            return new All(member);
        }

        public Only only(Member member) {
            return new Only(member, noteOnFilter);
        }

        public static class Only extends Handler<Member> {
            private final NoteOnFilter noteOnFilter;

            private Only(Member what, NoteOnFilter noteOnFilter) {
                super(what);
                this.noteOnFilter = noteOnFilter;
            }

            public List<NoteValue> noteStartValues() {
                return noteOnFilter.convert(get());
            }
        }

        public static class All extends Handler<Member> {
            private All(Member what) {
                super(what);
            }

            public List<NoteValue> noteValues() {
                return new ArrayList<NoteValue>(get().noteValues);
            }

            public List<NoteType> noteTypes() {
                return new ArrayList<NoteType>(get().noteTypes);
            }

            public List<Note> note() {
                List<Note> notes = new ArrayList<Note>(get().noteTypes.size());
                for (int index = 0; index < get().noteTypes.size(); index++) {
                    notes.add(new Note(get().noteValues.get(index), get().noteTypes.get(index)));
                }
                return new ArrayList<Note>(notes);
            }
        }

    }

}

