package com.ouchadam.fyp.analysis;

public class ContainedMidiNote extends MidiNote {

    private final long lengthInTicks;

    public static ContainedMidiNote from(MidiNote noteOn, MidiNote noteOff) {
        long lengthInTicks = noteOff.getTick() - noteOn.getTick();
        return new ContainedMidiNote(noteOn.getKey(), noteOn.getOctave(), noteOn.getNote(), noteOn.getVelocity(), noteOn.getType(), noteOn.getTick(), lengthInTicks);
    }

    ContainedMidiNote(int key, int octave, int note, int velocity, Type type, long tick, long lengthInTicks) {
        super(key, octave, note, velocity, type, tick);
        this.lengthInTicks = lengthInTicks;
    }

    public long getTickLength() {
        return lengthInTicks;
    }

    public int lengthInSixteenth(int tickPerQuarter) {
        int barInTicks = tickPerQuarter * 4;
        int sixteenth = barInTicks / 16;
        return Math.round(lengthInTicks / sixteenth);
    }
}
