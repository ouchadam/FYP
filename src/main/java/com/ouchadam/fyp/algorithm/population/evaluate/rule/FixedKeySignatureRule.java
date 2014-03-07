package com.ouchadam.fyp.algorithm.population.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.Percentage;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessValue;
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
        List<Note> notes = what.all().notes();
        int percent = percent(notes, fixedKey);
        return new FitnessValue(percent);
    }

    private int percent(List<Note> notes, Key key) {
        List<Integer> matchResults = new ArrayList<Integer>(ScaleCreator.Type.values().length);
        for (ScaleCreator.Type type : ScaleCreator.Type.values()) {
            int[] intervals = scaleCreator.create(key, type);
            int matches = countScaleMatches(notes, intervals);
            if (notes.get(0).decimal() % 12 != key.value()) {
                matches--;
            }
            int percentage = Percentage.from(matches, notes.size());
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

    private int countScaleMatches(List<Note> notes, int[] intervals) {
        int matched = 0;
        for (int interval : intervals) {
            for (Note note : notes) {
                if (isPartOfScale(interval, note)) {
                    matched++;
                }
            }
        }
        return matched;
    }

    private boolean isPartOfScale(int interval, Note note) {
        return note.decimal() % 12 == interval;
    }
}
