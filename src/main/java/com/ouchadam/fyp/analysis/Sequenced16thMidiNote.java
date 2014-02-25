package com.ouchadam.fyp.analysis;

public class Sequenced16thMidiNote extends MidiNote {

    private final int position;
    private final int length;

    public static Sequenced16thMidiNote from(int ticksPerQuarter, ContainedMidiNote note) {
        int barInTicks = ticksPerQuarter * 4;
        int sixteenth = barInTicks / 16;
        int position = roundToSixteenth(sixteenth, note.getTick());
        int length = roundToSixteenth(sixteenth, note.getTickLength());
        return new Sequenced16thMidiNote(note.getKey(), note.getOctave(), note.getNote(), note.getVelocity(), note.getType(), note.getTick(), position, length);
    }

    private static int roundToSixteenth(int sixteenth, long ticks) {
        return Math.round(ticks / sixteenth);
    }

    Sequenced16thMidiNote(int key, int octave, int note, int velocity, Type type, long tick, int position, int length) {
        super(key, octave, note, velocity, type, tick);
        this.position = position;
        this.length = length;
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

}
