package com.ouchadam.fyp.analysis;

import com.ouchadam.fyp.Log;
import com.ouchadam.fyp.analysis.midi.BaseMidiNote;
import com.ouchadam.fyp.analysis.midi.MidiNote;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

public class MidiMessageMarshaller {

    public boolean isNoteMessage(ShortMessage message) {
        int command = message.getCommand();
        return command == ShortMessage.NOTE_ON || command == ShortMessage.NOTE_OFF;
    }

    public MidiNote toNote(ShortMessage message, long tick) {
        return BaseMidiNote.from(noteOffMarshaller(message), tick);
    }

    private ShortMessage noteOffMarshaller(ShortMessage message) {
        int command = replaceZeroVelocityWithNoteOff(message.getCommand(), message.getData2());
        try {
            return new ShortMessage(command, message.getChannel(), message.getData1(), message.getData2());
        } catch (InvalidMidiDataException e) {
            Log.e("Invalid midi data : Should never happen as the original message would not have been created", e);
            throw new RuntimeException("Invalid midi data", e);
        }
    }

    private int replaceZeroVelocityWithNoteOff(int command, int velocity) {
        return velocity == 0 && command == ShortMessage.NOTE_ON ? ShortMessage.NOTE_OFF : command;
    }

}
