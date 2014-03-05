package com.ouchadam.fyp.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {

    public static final int CHILD_COUNT = 8;

    private final List<Note> notes;
    private final Controller controller;

    public Member(List<Note> notes, Controller controller) {
        this.controller = controller;
        this.notes = Collections.unmodifiableList(notes);
    }

    public Note note(int index) {
        return notes.get(index);
    }

    public void forEvery(ForEvery<Integer, Note, Void> forEvery) {
        for (int index = 0; index < CHILD_COUNT; index++) {
            forEvery.on(index, note(index), null);
        }
    }

    public int size() {
        return CHILD_COUNT;
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

    public Controller.Each forEach() {
        return controller.forEach(this);
    }

    public Controller.All all() {
        return controller.all(this);
    }

    public static class Controller {

        public Each forEach(Member member) {
            return new Each(member);
        }

        public All all(Member member) {
            return new All(member);
        }

        public static class Each extends Handler<Member> {
            private Each(Member member) {
                super(member);
            }

            public void note(ForEach<Note> forEach) {
                for (Note note : get().notes) {
                    forEach.on(note);
                }
            }
        }

        public static class All extends Handler<Member> {
            private All(Member what) {
                super(what);
            }

            public List<Note> notes() {
                return new ArrayList<Note>(get().notes);
            }
        }

    }

}

