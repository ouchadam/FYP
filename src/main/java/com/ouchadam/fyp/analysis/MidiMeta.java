package com.ouchadam.fyp.analysis;

import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

public class MidiMeta {

    private final Division division;
    private final int resolution;
    private final long tickLength;
    private final int eventCount;
    private final int trackCount;
    private final int barCount;

    public static MidiMeta from(Sequence sequence, Track track) {
        return new MidiMeta(Division.from(sequence), sequence.getResolution(), track.ticks(), track.size(), sequence.getTracks().length, new BarCounter().getBarCount(sequence, track));
    }

    public MidiMeta(Division division, int resolution, long tickLength, int eventCount, int trackCount, int barCount) {
        this.division = division;
        this.resolution = resolution;
        this.tickLength = tickLength;
        this.eventCount = eventCount;
        this.trackCount = trackCount;
        this.barCount = barCount;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public Division getDivision() {
        return division;
    }

    public int getResolution() {
        return resolution;
    }

    public long getTickLength() {
        return tickLength;
    }

    public int getEventCount() {
        return eventCount;
    }

    public int getBarCount() {
        return barCount;
    }
}
