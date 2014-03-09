package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.NoteType;
import com.ouchadam.fyp.algorithm.NoteValue;
import com.ouchadam.fyp.analysis.midi.Sequenced16thMidiNote;

import java.util.ArrayList;
import java.util.List;

public class MemberToMidi {

    private static final int TICKS_PER_QUARTER = 960;
    private static final int VELOCITY = 0x60;

    public List<Sequenced16thMidiNote> convert(Member member) {
        List<Sequenced16thMidiNote> sequencedMidiNotes = new ArrayList<Sequenced16thMidiNote>(member.size());
        System.out.println("");
        System.out.println("Converting to midinotes");
        System.out.println("Member has types : " + member.all().noteTypes().size());
        System.out.println("");

        int length = 1;
        int restOffset = 0;

        for (int index = 1; index < member.size(); index++) {

            int position = restOffset + index;

            NoteType previousType = member.type(index - 1);
            NoteValue previousValue = member.note(index - 1);

            System.out.println(previousValue.decimal() + " " + previousType);

            NoteType currentType = member.type(index);

            if (previousType == NoteType.HOLD && (currentType == NoteType.REST || currentType == NoteType.NOTE)) {
                System.out.println("Insert : " + previousType + " with position : " + (position - length) + " index : " + index);
                sequencedMidiNotes.add(createMidiNote(length, position - length, previousValue));
            }


            if (previousType == NoteType.NOTE && currentType == NoteType.NOTE) {
                System.out.println("Insert : " + previousType + " with position : " + (position - length) + " index : " + index);
                sequencedMidiNotes.add(createMidiNote(length, position - length, previousValue));
            }

            switch (currentType) {
                case HOLD:
                    length++;
                    break;

                case NOTE:
                    length = 1;
                    break;
            }

            if (index == member.size() - 1 && currentType != NoteType.REST) {
                sequencedMidiNotes.add(createMidiNote(length, position, previousValue));
            }

        }

        System.out.println("Result has size of : " + sequencedMidiNotes.size());
        return sequencedMidiNotes;
    }

    private Sequenced16thMidiNote createMidiNote(int length, int position, NoteValue previousValue) {
        return Sequenced16thMidiNote.from(position, length, previousValue.decimal(), VELOCITY, TICKS_PER_QUARTER);
    }

}
