package com.ouchadam.fyp.analysis;

import com.ouchadam.fyp.analysis.midi.MidiNote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeyAnalysis implements AnalysisRule<MidiNote> {

    private final ScaleCreator scaleCreator;

    public KeyAnalysis(ScaleCreator scaleCreator) {
        this.scaleCreator = scaleCreator;
    }

    @Override
    public String apply(List<? extends MidiNote> notes) {
        Result keyResult = analyse(notes);
        return "Key Likelyhood : " + keyResult.key + " " + keyResult.type + " " + keyResult.percent + "%";
    }

    Result analyse(List<? extends MidiNote> midiNotes) {
        return percent(midiNotes);
    }

    private Result percent(List<? extends MidiNote> notes) {
        List<Result> matchResults = new ArrayList<Result>(Key.values().length * ScaleCreator.Type.values().length);
        for (ScaleCreator.Type type : ScaleCreator.Type.values()) {
            for (Key key : Key.values()) {
                int matched = countScaleMatches(notes, scaleCreator.create(key, type));
                matchResults.add(new Result(key, type, createPercentageOfMatches(notes.size(), matched)));
            }
        }
        return getBestResult(notes, matchResults);
    }

    private int createPercentageOfMatches(int size, float matched) {
        return Math.round((matched / (float) size) * 100);
    }

    private Result getBestResult(List<? extends MidiNote> notes, List<Result> matchResults) {
        List<Result> sortedResults = sortResults(matchResults);
        return hasMultipleEqualResults(sortedResults) ? biasToFirstNote(notes, sortedResults) : sortedResults.get(0);
    }

    private List<Result> sortResults(List<Result> matchResults) {
        Collections.sort(matchResults);
        return Collections.unmodifiableList(matchResults);
    }

    private int countScaleMatches(List<? extends MidiNote> notes, int[] intervals) {
        int matched = 0;
        for (int interval : intervals) {
            for (MidiNote note : notes) {
                if (isPartOfScale(interval, note)) {
                    matched++;
                }
            }
        }
        return matched;
    }

    private boolean isPartOfScale(int interval, MidiNote midiNote) {
        return midiNote.getNote().value() == interval;
    }

    private boolean hasMultipleEqualResults(List<Result> results) {
        int equalResultCount = 0;
        int topResultPercent = results.get(0).percent;
        if (results.size() > 1) {
            for (Result result : results) {
                if (result.percent == topResultPercent) {
                    equalResultCount++;
                }
            }
        }
        return equalResultCount > 1;
    }

    private Result biasToFirstNote(List<? extends MidiNote> notes, List<Result> matchResults) {
        for (MidiNote note : notes) {
            for (Result matchResult : matchResults) {
                if (note.getNote() == matchResult.key) {
                    return matchResult;
                }
            }
        }
        throw new RuntimeException("Result doesn't exist within the midi notes");
    }

    public static class Result implements Comparable<Result> {
        final Key key;
        final ScaleCreator.Type type;
        final int percent;

        Result(Key key, ScaleCreator.Type type, int percent) {
            this.key = key;
            this.type = type;
            this.percent = percent;
        }

        @Override
        public int compareTo(Result o) {
            if (this.percent == o.percent) {
                return 0;
            }
            return o.percent < this.percent ? -1 : 1;
        }
    }

}
