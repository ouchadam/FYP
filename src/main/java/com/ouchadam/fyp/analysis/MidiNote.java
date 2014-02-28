package com.ouchadam.fyp.analysis;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;

public class MidiNote {

    private static final int TWELVE_TONE_SCALE = 12;

    public enum Type {
        ON {
            @Override
            public int asStatus() {
                return ShortMessage.NOTE_ON;
            }
        },
        OFF {
            @Override
            public int asStatus() {
                return ShortMessage.NOTE_OFF;
            }
        };

        public abstract int asStatus();

        static Type from(ShortMessage message) {
            switch (message.getCommand()) {

                case ShortMessage.NOTE_ON:
                    return ON;

                case ShortMessage.NOTE_OFF:
                    return OFF;

                default:
                    throw new RuntimeException("Tried to create a midi note with a unhandled command : " + message.getCommand());
            }
        }

    }

    private final int key;
    private final int octave;
    private final Key note;
    private final int velocity;
    private final Type type;
    private final long tick;

    public static MidiNote from(ShortMessage message, long tick) {
        Type type = Type.from(message);
        int key = message.getData1();
        int noteValue = key % TWELVE_TONE_SCALE;
        int octave = (key / TWELVE_TONE_SCALE) - 1;
        int velocity = message.getData2();
        return new MidiNote(key, octave, Key.from(noteValue), velocity, type, tick);
    }

    public static MidiNote from(MidiNote midiNote) {
        return new MidiNote(midiNote.key, midiNote.octave, midiNote.note, midiNote.velocity, midiNote.type, midiNote.tick);
    }

    MidiNote(int key, int octave, Key note, int velocity, Type type, long tick) {
        this.key = key;
        this.octave = octave;
        this.note = note;
        this.velocity = velocity;
        this.type = type;
        this.tick = tick;
    }

    public int getKey() {
        return key;
    }

    public Type getType() {
        return type;
    }

    public long getTick() {
        return tick;
    }

    public int getOctave() {
        return octave;
    }

    public Key getNote() {
        return note;
    }

    public int getVelocity() {
        return velocity;
    }

    public MidiEvent toMessage() {
        return createEvent(getType().asStatus(), getKey(), getVelocity(), getTick());
    }

    protected MidiEvent createEvent(int status, int data1, int data2, long tick) {
        try {
            return new MidiEvent(new ShortMessage(status, data1, data2), tick);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid midi event");
        }
    }

}
