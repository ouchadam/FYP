package com.ouchadam.fyp.presentation.view;

import com.ouchadam.fyp.algorithm.NoteValue;

class Range {

    private int highest;
    private int lowest;

    Range() {
        this(NoteValue.NOTE_MIN, NoteValue.NOTE_MAX);
    }

    Range(int highest, int lowest) {
        this.highest = highest;
        this.lowest = lowest;
    }

    public void setLowest(int lowest) {
        this.lowest = lowest;
    }

    public void setHighest(int highest) {
        this.highest = highest;
    }

    public int getLowest() {
        return lowest;
    }

    public int getHighest() {
        return highest;
    }
}
