package com.ouchadam.fyp.analysis.midi;

import com.ouchadam.fyp.analysis.Key;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;

public class BaseMidiNote implements MidiNote {

    private static final int TWELVE_TONE_SCALE = 12;

    private final int key;
    private final int octave;
    private final Key note;
    private final int velocity;
    private final Type type;
    private final long tick;

    public static MidiNote from(Type type, int key, int velocity, long tick) {
        int noteValue = key % TWELVE_TONE_SCALE;
        int octave = (key / TWELVE_TONE_SCALE) - 1;
        return new BaseMidiNote(key, octave, Key.from(noteValue), velocity, type, tick);
    }

    public static MidiNote from(ShortMessage message, long tick) {
        Type type = Type.from(message);
        int key = message.getData1();
        int noteValue = key % TWELVE_TONE_SCALE;
        int octave = (key / TWELVE_TONE_SCALE) - 1;
        int velocity = message.getData2();
        return new BaseMidiNote(key, octave, Key.from(noteValue), velocity, type, tick);
    }

    public static MidiNote from(BaseMidiNote midiNote) {
        return new BaseMidiNote(midiNote);
    }

    BaseMidiNote(int key, int octave, Key note, int velocity, Type type, long tick) {
        this.key = key;
        this.octave = octave;
        this.note = note;
        this.velocity = velocity;
        this.type = type;
        this.tick = tick;
    }

    BaseMidiNote(MidiNote midiNote) {
        this.key = midiNote.getKey();
        this.octave = midiNote.getOctave();
        this.note = midiNote.getNote();
        this.velocity = midiNote.getVelocity();
        this.type = midiNote.getType();
        this.tick = midiNote.getTick();
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public long getTick() {
        return tick;
    }

    @Override
    public int getOctave() {
        return octave;
    }

    @Override
    public Key getNote() {
        return note;
    }

    @Override
    public int getVelocity() {
        return velocity;
    }

    @Override
    public MidiEvent toMessage() {
        return createEvent(getType().asStatus(), getKey(), getVelocity(), getTick());
    }

    protected MidiEvent createEvent(int status, int data1, int data2, long tick) {
        try {
            return new MidiEvent(new ShortMessage(status, 0, data1, data2), tick);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid midi event");
        }
    }

}
