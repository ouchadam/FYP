package com.ouchadam.fyp.presentation.midi;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.NoteType;
import com.ouchadam.fyp.algorithm.NoteValue;

import java.util.ArrayList;
import java.util.List;

public class NoteOnFilter {

    private static final int FIRST_INDEX = 0;

    public List<NoteValue> convert(Member member) {
        List<NoteValue> sequencedMidiNotes = new ArrayList<NoteValue>();

        List<NoteType> noteTypes = member.all().noteTypes();
        List<NoteValue> noteValues = member.all().noteValues();

        NotePositionConverter notePositionConverter = new NotePositionConverter();

        for (int index = FIRST_INDEX; index < member.size(); index++) {

            if (index == 0) {
                if (noteTypes.get(index) == NoteType.HOLD || noteTypes.get(index) == NoteType.NOTE) {
                    notePositionConverter.start(noteValues.get(index));
                }
                continue;
            }

            if (noteTypes.get(index) == NoteType.NOTE) {
                if (notePositionConverter.isStarted()) {
                    sequencedMidiNotes.add(notePositionConverter.collect());
                }
                notePositionConverter.start(noteValues.get(index));
            }

            if (noteTypes.get(index) == NoteType.HOLD) {
                if (!notePositionConverter.isStarted()) {
                    notePositionConverter.start(noteValues.get(index));
                }
            }

            if (noteTypes.get(index) == NoteType.REST) {
                if (notePositionConverter.isStarted()) {
                    sequencedMidiNotes.add(notePositionConverter.collect());
                }
            }

            if (isLastPosition(member, index) && notePositionConverter.isStarted()) {
                sequencedMidiNotes.add(notePositionConverter.collect());
            }

        }

        return sequencedMidiNotes;
    }

    private boolean isLastPosition(Member member, int index) {
        return index == (member.size() - 1);
    }

    private static class NotePositionConverter {

        private NoteValue noteValue;
        private boolean started;

        public void start(NoteValue noteValue) {
            this.started = true;
            this.noteValue = noteValue;
        }

        public boolean isStarted() {
            return this.started;
        }

        public NoteValue collect() {
            this.started = false;
            return noteValue;
        }
    }

}
