package com.ouchadam.fyp.presentation.tab;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class NoteRangeTest {

    @Test
    public void find_the_correct_range_size() {
        NoteRange noteRange = new NoteRange(createRange(20, 30));

        assertThat(noteRange.size()).isEqualTo(10);
    }

    @Test
    public void lowest_value_should_be_0() {
        NoteRange noteRange = new NoteRange(createRange(69, 49));

        int rangedNote = noteRange.applyRange(49);

        assertThat(rangedNote).isEqualTo(0);
    }

    @Test
    public void inverted_lowest_value_should_be_the_size() {
        NoteRange noteRange = new NoteRange(createRange(69, 49));

        int rangedNote = noteRange.applyInvertedRange(49);

        assertThat(rangedNote).isEqualTo(noteRange.size());
    }

    @Test
    public void highest_value_should_be_the_size() {
        NoteRange noteRange = new NoteRange(createRange(69, 49));

        int rangedNote = noteRange.applyRange(69);

        assertThat(rangedNote).isEqualTo(noteRange.size());
    }

    @Test
    public void inverted_highest_value_should_be_0() {
        NoteRange noteRange = new NoteRange(createRange(69, 49));

        int rangedNote = noteRange.applyInvertedRange(69);

        assertThat(rangedNote).isEqualTo(0);
    }

    private Range createRange(int highest, int lowest) {
        return new Range(highest, lowest);
    }

}
