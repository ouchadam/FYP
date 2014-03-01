package com.ouchadam.fyp.algorithm.population.evaluate.fitness;

import com.ouchadam.fyp.algorithm.IndexManager;
import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.analysis.Key;
import com.ouchadam.fyp.analysis.MidiNote;
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
        List<Note> notes = what.getAll().notes();
        return new FitnessValue(percent(notes, fixedKey));
    }

    private int percent(List<Note> notes, Key key) {
        List<Integer> matchResults = new ArrayList<Integer>(ScaleCreator.Type.values().length);
        for (ScaleCreator.Type type : ScaleCreator.Type.values()) {
            int matched = countScaleMatches(notes, scaleCreator.create(key, type));
            matchResults.add(createPercentageOfMatches(notes.size(), matched));
        }
        return getBestResult(matchResults);
    }

    private int createPercentageOfMatches(int size, float matched) {
        return Math.round((matched / (float) size) * 100);
    }

    private int getBestResult(List<Integer> matchResults) {
        List<Integer> sortedResults = sortResults(matchResults);
        return sortedResults.get(0);
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
        return (note.decimal() % 12) == interval;
    }

}
