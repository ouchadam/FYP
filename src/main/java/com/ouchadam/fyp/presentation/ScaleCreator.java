package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.Key;

public class ScaleCreator {

    public enum Type {

        MAJOR(new int[]{0, 2, 4, 5, 7, 9, 11}),
        MINOR_MELODIC(new int[]{0, 2, 3, 5, 7, 9, 11}),
        MINOR_NATURAL(new int[]{0, 2, 3, 5, 7, 8, 10}),
        MINOR_HARMONIC(new int[]{0, 2, 3, 4, 8, 9, 11});

        final int[] intervals;

        Type(int[] intervals) {
            this.intervals = intervals.clone();
        }

        public int size() {
            return intervals.length;
        }
    }

    public int[] create(Key key, Type type) {
        int[] scaleIntervals = new int[type.size()];
        for (int index = 0; index < scaleIntervals.length; index++) {
            scaleIntervals[index] = wrap12ToneScale(key, type.intervals[index]);
        }
        return scaleIntervals;
    }

    private int wrap12ToneScale(Key key, int value) {
        if (value + key.value() >= 12) {
            return value + key.value() - 12;
        }
        return value + key.value();
    }

}
