package com.ouchadam.fyp.analysis;

import java.util.List;

public class MidiTrack {

    private final MidiMeta meta;
    private final List<MidiNote> notes;

    public MidiTrack(MidiMeta meta, List<MidiNote> notes) {
        this.meta = meta;
        this.notes = notes;
    }

    public MidiMeta getMeta() {
        return meta;
    }

    public List<MidiNote> getNotes() {
        return notes;
    }
}
