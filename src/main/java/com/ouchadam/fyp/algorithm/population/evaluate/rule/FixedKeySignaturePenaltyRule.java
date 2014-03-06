package com.ouchadam.fyp.algorithm.population.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessValue;
import com.ouchadam.fyp.analysis.Key;
import com.ouchadam.fyp.presentation.ScaleCreator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FixedKeySignaturePenaltyRule implements FitnessRule<Member> {

    private final Key fixedKey;
    private final ScaleCreator scaleCreator;

    public static FixedKeySignaturePenaltyRule newInstance(Key key) {
        return new FixedKeySignaturePenaltyRule(key, new ScaleCreator());
    }

    public FixedKeySignaturePenaltyRule(Key fixedKey, ScaleCreator scaleCreator) {
        this.fixedKey = fixedKey;
        this.scaleCreator = scaleCreator;
    }

    @Override
    public FitnessValue apply(Member what) {
        List<Note> notes = what.all().notes();
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
        int delta = (int) Math.abs(size - matched);
        int penalty = (delta * 5);
        int percent =  Math.round((matched / (float) size) * 100);
        return percent - (penalty * delta);
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
//
//        for (Note note : notes) {
//            for (int interval : intervals) {
//
//                scaleDelta()
//
//            }
//
//        }
//
//
//        int matched = 0;
//        for (int interval : intervals) {
//            for (Note note : notes) {
//                if (scaleDelta(interval, note)) {
//                    matched++;
//                }
//            }
//        }
        return 0;
    }

    private int scaleDelta(int interval, Note note) {
        return (note.decimal() % 12) - interval;
    }

}
