package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.*;
import com.ouchadam.fyp.analysis.midi.Sequenced16thMidiNote;

import java.util.ArrayList;
import java.util.List;

public class MemberToMidi {

    private static final int TICKS_PER_QUARTER = 960;
    private static final int VELOCITY = 0x60;
    private static final int FIRST_INDEX = 0;

    public List<Sequenced16thMidiNote> convert(Member member) {
        List<Sequenced16thMidiNote> sequencedMidiNotes = new ArrayList<Sequenced16thMidiNote>(member.size());

        List<NoteType> noteTypes = member.all().noteTypes();
        List<NoteValue> noteValues = member.all().noteValues();

        FB fb = new FB();

        for (int index = FIRST_INDEX; index < member.size(); index++) {

            if (index == 0) {
                if (noteTypes.get(index) == NoteType.HOLD || noteTypes.get(index) == NoteType.NOTE) {
                    fb.start(noteValues.get(index));
                }
                continue;
            }

            if (noteTypes.get(index) == NoteType.NOTE) {
                if (fb.isStarted()) {
                    sequencedMidiNotes.add(fb.collect(index));
                }
                fb.start(noteValues.get(index));
            }

            if (noteTypes.get(index) == NoteType.HOLD) {
                if (fb.isStarted()) {
                    fb.continues();
                } else {
                    fb.start(noteValues.get(index));
                }
            }

            if (noteTypes.get(index) == NoteType.REST) {
                if (fb.isStarted()) {
                    sequencedMidiNotes.add(fb.collect(index));
                }
            }

            if (isLastPosition(member, index) && fb.isStarted()) {
                sequencedMidiNotes.add(fb.collect(index + 1));
            }

        }

        return sequencedMidiNotes;
    }

    private boolean isLastPosition(Member member, int index) {
        return index == (member.size() - 1);
    }

    private static class FB {

        private int length;
        private NoteValue noteValue;
        private boolean started;

        public void start(NoteValue noteValue) {
            this.started = true;
            this.noteValue = noteValue;
            this.length = 1;
        }

        public void continues() {
            this.length++;
        }

        public boolean isStarted() {
            return this.started;
        }

        public Sequenced16thMidiNote collect(int endPosition) {
            this.started = false;
            return Sequenced16thMidiNote.from(endPosition - length, length, noteValue.decimal(), VELOCITY, TICKS_PER_QUARTER);
        }
    }

}
