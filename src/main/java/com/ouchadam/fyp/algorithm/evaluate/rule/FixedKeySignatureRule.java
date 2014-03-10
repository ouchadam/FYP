package com.ouchadam.fyp.algorithm.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.NoteValue;
import com.ouchadam.fyp.algorithm.Percentage;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;
import com.ouchadam.fyp.analysis.Key;
import com.ouchadam.fyp.presentation.ScaleCreator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FixedKeySignatureRule implements FitnessRule<Member> {

    private final Key fixedKey;
    private final ScaleCreator scaleCreator;

    public static FixedKeySignatureRule newInstance(Key key) {
        return new FixedKeySignatureRule(key, new ScaleCreator());
    }

    public FixedKeySignatureRule(Key fixedKey, ScaleCreator scaleCreator) {
        this.fixedKey = fixedKey;
        this.scaleCreator = scaleCreator;
    }

    @Override
    public FitnessValue apply(Member what) {
        List<NoteValue> noteValues = what.only().noteStartValues();
        if (noteValues.isEmpty()) {
            return FitnessValue.min();
        }

        int percent = percent(noteValues, fixedKey);
        return new FitnessValue(percent);
    }

    private int percent(List<NoteValue> noteValues, Key key) {
        List<Integer> matchResults = new ArrayList<Integer>(ScaleCreator.Type.values().length);
        for (ScaleCreator.Type type : ScaleCreator.Type.values()) {
            int[] intervals = scaleCreator.create(key, type);
            int matches = countScaleMatches(noteValues, intervals);
            if (noteValues.get(0).decimal() % 12 != key.value()) {
                matches--;
            }
            int percentage = Percentage.from(matches, noteValues.size());
            matchResults.add(percentage);
        }
        return getBestResult(matchResults);
    }

    private int getBestResult(List<Integer> matchResults) {
        List<Integer> sortedResults = sortResults(matchResults);
        return sortedResults.get(sortedResults.size() - 1);
    }

    private List<Integer> sortResults(List<Integer> matchResults) {
        Collections.sort(matchResults);
        return Collections.unmodifiableList(matchResults);
    }

    private int countScaleMatches(List<NoteValue> noteValues, int[] intervals) {
        int matched = 0;
        for (int interval : intervals) {
            for (NoteValue noteValue : noteValues) {
                if (isPartOfScale(interval, noteValue)) {
                    matched++;
                }
            }
        }
        return matched;
    }

    private boolean isPartOfScale(int interval, NoteValue noteValue) {
        return noteValue.decimal() % 12 == interval;
    }
}
