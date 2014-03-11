package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.NoteType;
import com.ouchadam.fyp.algorithm.NoteValue;
import com.ouchadam.fyp.analysis.midi.Sequenced16thMidiNote;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import helper.TestWithMocks;

import static org.fest.assertions.api.Assertions.assertThat;

public class MemberToMidiTest extends TestWithMocks {

    private MemberToMidi memberToMidi;

    @Override
    protected void before() {
        memberToMidi = new MemberToMidi();
    }

    @Test
    public void combine_note_holds_into_1_note() throws Exception {
        Note on = createNotes(60, NoteType.NOTE);
        Note hold = createNotes(60, NoteType.HOLD);
        Note hold2 = createNotes(60, NoteType.HOLD);
        Note hold3 = createNotes(60, NoteType.HOLD);

        Member member = createMember(on, hold, hold2, hold3);

        List<Sequenced16thMidiNote> midiNotes = memberToMidi.convert(member);

        assertThat(midiNotes).hasSize(1);
    }

    @Test
    public void ignore_note_rests_() throws Exception {
        Note on = createNotes(60, NoteType.NOTE);
        Note hold = createNotes(60, NoteType.HOLD);
        Note rest = createNotes(60, NoteType.REST);
        Note rest2 = createNotes(60, NoteType.REST);

        Member member = createMember(on, hold, rest, rest2);

        List<Sequenced16thMidiNote> midiNotes = memberToMidi.convert(member);

        assertThat(midiNotes).hasSize(1);
    }

    @Test
    public void complex() throws Exception {
        Note on = createNotes(60, NoteType.NOTE);
        Note hold = createNotes(60, NoteType.HOLD);
        Note rest = createNotes(60, NoteType.REST);
        Note on2 = createNotes(60, NoteType.NOTE);
        Note hold2 = createNotes(60, NoteType.HOLD);
        Note hold3 = createNotes(60, NoteType.HOLD);
        Note rest3 = createNotes(60, NoteType.REST);
        Note rest4 = createNotes(60, NoteType.REST);
        Note on3 = createNotes(60, NoteType.NOTE);

        Member member = createMember(on, hold, rest, on2, hold2, hold3, rest3, rest4, on3);

        List<Sequenced16thMidiNote> midiNotes = memberToMidi.convert(member);

        assertThat(midiNotes).hasSize(3);

        assertThatNote(midiNotes.get(0), 0, 2);
        assertThatNote(midiNotes.get(1), 3, 3);
        assertThatNote(midiNotes.get(2), 8, 1);
    }

    @Test
    public void complex2() throws Exception {
        Note n1 = createNotes(68, NoteType.NOTE);
        Note n2 = createNotes(84, NoteType.NOTE);
        Note n3 = createNotes(73, NoteType.HOLD);
        Note n4 = createNotes(109, NoteType.HOLD);
        Note n5 = createNotes(76, NoteType.NOTE);
        Note n6 = createNotes(94, NoteType.HOLD);
        Note n7 = createNotes(98, NoteType.HOLD);
        Note n8 = createNotes(65, NoteType.NOTE);
        Note n9 = createNotes(88, NoteType.HOLD);
        Note n10 = createNotes(72, NoteType.HOLD);
        Note n11 = createNotes(105, NoteType.HOLD);
        Note n12 = createNotes(87, NoteType.NOTE);
        Note n13 = createNotes(119, NoteType.NOTE);
        Note n14 = createNotes(81, NoteType.NOTE);
        Note n15 = createNotes(106, NoteType.HOLD);

        Member member = createMember(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15);

        List<Sequenced16thMidiNote> midiNotes = memberToMidi.convert(member);

        assertThat(midiNotes).hasSize(7);

        assertThatNote(midiNotes.get(0), 0, 1);
        assertThatNote(midiNotes.get(1), 1, 3);
        assertThatNote(midiNotes.get(2), 4, 3);
    }

    @Test
    public void complex3() throws Exception {
        Note n1 = createNotes(68, NoteType.HOLD);
        Note n2 = createNotes(84, NoteType.HOLD);
        Note n3 = createNotes(73, NoteType.HOLD);
        Note n4 = createNotes(109, NoteType.NOTE);
        Note n5 = createNotes(76, NoteType.HOLD);
        Note n6 = createNotes(94, NoteType.REST);
        Note n7 = createNotes(98, NoteType.HOLD);
        Note n8 = createNotes(65, NoteType.NOTE);

        Member member = createMember(n1, n2, n3, n4, n5, n6, n7, n8);

        List<Sequenced16thMidiNote> midiNotes = memberToMidi.convert(member);

        assertThat(midiNotes).hasSize(4);

        assertThatNote(midiNotes.get(0), 0, 3);
        assertThatNote(midiNotes.get(1), 3, 2);
        assertThatNote(midiNotes.get(2), 6, 1);
        assertThatNote(midiNotes.get(3), 7, 1);
    }

    @Test
    public void hold_with_no_preceeding_note_should_be_treated_as_notes() throws Exception {
        Note n1 = createNotes(68, NoteType.HOLD);
        Note n2 = createNotes(84, NoteType.HOLD);
        Note n3 = createNotes(73, NoteType.HOLD);
        Note n4 = createNotes(109, NoteType.REST);
        Note n5 = createNotes(76, NoteType.HOLD);
        Note n6 = createNotes(109, NoteType.REST);
        Note n7 = createNotes(98, NoteType.HOLD);

        Member member = createMember(n1, n2, n3, n4, n5, n6, n7);

        List<Sequenced16thMidiNote> midiNotes = memberToMidi.convert(member);

        assertThat(midiNotes).hasSize(3);

        assertThatNote(midiNotes.get(0), 0, 3);
        assertThatNote(midiNotes.get(1), 4, 1);
        assertThatNote(midiNotes.get(2), 6, 1);
    }

    private static void assertThatNote(Sequenced16thMidiNote note, int position, int length) {
        assertThat(note.position()).isEqualTo(position);
        assertThat(note.length()).isEqualTo(length);
    }

    private static Member createMember(Note... notes) {
        List<NoteValue> noteValues = new ArrayList<NoteValue>();
        List<NoteType> noteTypes = new ArrayList<NoteType>();
        for (Note note : notes) {
            noteValues.add(note.noteValue);
            noteTypes.add(note.noteType);
        }
        return new Member(noteValues, noteTypes, new Member.Controller(new NoteOnFilter()));
    }

    private static Note createNotes(int value, NoteType type) {
        return new Note(NoteValue.newInstance(value), type);
    }


}
