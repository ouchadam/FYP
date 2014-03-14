package com.ouchadam.fyp.algorithm.domain;

public class Note {

    public final NoteValue noteValue;
    public final NoteType noteType;

    public Note(NoteValue noteValue, NoteType noteType) {
        this.noteValue = noteValue;
        this.noteType = noteType;
    }
}
