package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.Log;
import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.analysis.Division;
import com.ouchadam.fyp.analysis.midi.Sequenced16thMidiNote;

import javax.sound.midi.*;
import java.util.List;

class MemberToSequence {

    private static final int TRACK_COUNT = 1;
    private static final int RESOLUTION = 960;
    private static final Division DIVISION = Division.PPQ;
    public static final int CHANNEL = 0;
    public static final int PROGRAM_NUMBER = 20;

    private final MemberToMidi memberToMidi;

    MemberToSequence(MemberToMidi memberToMidi) {
        this.memberToMidi = memberToMidi;
    }

    public Sequence from(Member member) {
        try {
            return createSequenceFrom(memberToMidi.convert(member));
        } catch (Exception e) {
            throw new RuntimeException("Couldn't create sequence from provided notes", e);
        }
    }

    private Sequence createSequenceFrom(List<Sequenced16thMidiNote> notes) throws Exception {
        Sequence sequence = new Sequence(DIVISION.value(), RESOLUTION, TRACK_COUNT);
        Track track = getFirstTrack(sequence);
        track.add(createProgramChange(PROGRAM_NUMBER));
        for (Sequenced16thMidiNote midiNote : notes) {
            System.out.println("Adding : " + midiNote.getNote() + "(" + midiNote.getKey() + ")" + " at " + midiNote.getTick() + " : " + midiNote.getType() + " to : " + midiNote.getNoteOff().getTick());
            track.add(midiNote.getNoteOn());
            track.add(midiNote.getNoteOff());
        }
        padToEnd(notes, track);
        return sequence;
    }

    private void padToEnd(List<Sequenced16thMidiNote> notes, Track track) throws InvalidMidiDataException {
        if (notes.get(notes.size() - 1).position() < 15) {
            Log.i("Sequence is short, padding with end event");
            long tick = (960 * 4) / 16 * 15;
            track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, 0, 0x00, 0x00), tick));
        }
    }

    private Track getFirstTrack(Sequence sequence) {
        return sequence.getTracks()[0];
    }

    private MidiEvent createProgramChange(int programNumber) throws InvalidMidiDataException {
        return new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, CHANNEL, programNumber, 0), 0);
    }

}
