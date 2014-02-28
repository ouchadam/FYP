package com.ouchadam.fyp.analysis;

import javax.sound.midi.MidiEvent;

public class Sequenced16thMidiNote extends MidiNote {

    private final int position;
    private final int length;
    private final long ticklength;

    public static Sequenced16thMidiNote from(int ticksPerQuarter, ContainedMidiNote note) {
        int barInTicks = ticksPerQuarter * 4;
        int sixteenth = barInTicks / 16;
        int position = roundToSixteenth(sixteenth, note.getTick());
        int length = roundToSixteenth(sixteenth, note.getTickLength());
        long tickLength = note.getTickLength();
        return new Sequenced16thMidiNote(note.getKey(), note.getOctave(), note.getNote(), note.getVelocity(), note.getType(), note.getTick(), position, length, tickLength);
    }

    private static int roundToSixteenth(int sixteenth, long ticks) {
        return Math.round(ticks / sixteenth);
    }

    Sequenced16thMidiNote(int key, int octave, int note, int velocity, Type type, long tick, int position, int length, long tickLength) {
        super(key, octave, note, velocity, type, tick);
        this.position = position;
        this.length = length;
        this.ticklength = tickLength;
    }

    public int bar() {
        int rawBar = position % 16;
        return rawBar < 0 ? 0 : rawBar;
    }

    public int position() {
        return position;
    }

    public int length() {
        return length;
    }

    public MidiEvent getNoteOn() {
        return createEvent(Type.ON.asStatus(), getKey(), getVelocity(), getTick());
    }

    public MidiEvent getNoteOff() {
        return createEvent(Type.OFF.asStatus(), getKey(), getVelocity(), getEndTick());
    }

    private long getEndTick() {
        return getTick() + ticklength;
    }

}
