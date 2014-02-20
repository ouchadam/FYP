package com.ouchadam.fyp.analysis;

import javax.sound.midi.ShortMessage;

public class MidiNote {

    private static final int TWELVE_TONE_SCALE = 12;

    enum Type {
        ON, OFF;

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
    private final int note;
    private final int velocity;
    private final Type type;
    private final long tick;

    public static MidiNote from(ShortMessage message, long tick) {
        Type type = Type.from(message);
        int key = message.getData1();
        int note = key % TWELVE_TONE_SCALE;
        int octave = (key / TWELVE_TONE_SCALE) - 1;
        int velocity = message.getData2();
        return new MidiNote(key, octave, note, velocity, type, tick);
    }

    public static MidiNote from(MidiNote midiNote) {
        return new MidiNote(midiNote.key, midiNote.octave, midiNote.note, midiNote.velocity, midiNote.type, midiNote.tick);
    }

    MidiNote(int key, int octave, int note, int velocity, Type type, long tick) {
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

    public int getNote() {
        return note;
    }

    public int getVelocity() {
        return velocity;
    }

}
