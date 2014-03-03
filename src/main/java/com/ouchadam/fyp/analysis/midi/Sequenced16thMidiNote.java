package com.ouchadam.fyp.analysis.midi;

import javax.sound.midi.MidiEvent;

public class Sequenced16thMidiNote extends BaseMidiNote {

    private final int position;
    private final int length;
    private final long ticklength;

    public static Sequenced16thMidiNote from(int position, int length, int noteValue, int velocity, int ticksPerQuarter) {
        int sixteenthTicks = getSixteenthInTicks(ticksPerQuarter);
        MidiNote note = from(Type.ON, noteValue, velocity, sixteenthTicks * position);
        long tickLength = (sixteenthTicks * length);
        return new Sequenced16thMidiNote(note, position, length, tickLength);
    }

    public static Sequenced16thMidiNote from(int ticksPerQuarter, long lengthInTicks, BaseMidiNote note) {
        int sixteenth = getSixteenthInTicks(ticksPerQuarter);
        int position = roundToSixteenth(sixteenth, note.getTick());
        int length = roundToSixteenth(sixteenth, lengthInTicks);
        return new Sequenced16thMidiNote(note, position, length, lengthInTicks);
    }

    private static int getSixteenthInTicks(int ticksPerQuarter) {
        int barInTicks = ticksPerQuarter * 4;
        return barInTicks / 16;
    }

    private static int roundToSixteenth(int sixteenth, long ticks) {
        return Math.round(ticks / sixteenth);
    }

    Sequenced16thMidiNote(MidiNote midiNote, int position, int length, long tickLength) {
        super(midiNote);
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
