package com.ouchadam.fyp.presentation.view;

class NoteRange {

    private final Range range;

    NoteRange(Range range) {
        this.range = range;
    }

    public int size() {
        return Math.abs(range.getHighest() - range.getLowest());
    }

    public int applyRange(int noteValue) {
        return (noteValue - range.getLowest());
    }

    public int applyInvertedRange(int noteValue) {
        return size() - applyRange(noteValue);
    }

}
