package com.ouchadam.fyp.analysis;

import helper.TestWithMocks;
import org.junit.Test;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MidiMessageMarshallerTest extends TestWithMocks {

    private MidiMessageMarshaller midiMessageMarshaller;

    @Override
    protected void before() {
        midiMessageMarshaller = new MidiMessageMarshaller();
    }

    @Test
    public void isNoteMessage_should_not_pass_for_non_note_onoff_messages() {
        ShortMessage message = createMessageWithCommand(ShortMessage.PITCH_BEND);

        assertThat(midiMessageMarshaller.isNoteMessage(message)).isFalse();
    }

    @Test
    public void isNoteMessage_should_pass_for_note_on_messages() {
        ShortMessage message = createMessageWithCommand(ShortMessage.NOTE_ON);

        assertThat(midiMessageMarshaller.isNoteMessage(message)).isTrue();
    }


    @Test
    public void isNoteMessage_should_pass_for_note_off_messages() {
        ShortMessage message = createMessageWithCommand(ShortMessage.NOTE_OFF);

        assertThat(midiMessageMarshaller.isNoteMessage(message)).isTrue();
    }

    @Test
    public void testName() throws InvalidMidiDataException {
        long tick = 0;
        ShortMessage message = new ShortMessage(ShortMessage.NOTE_ON, 0, 0x40, 0x60);

        assertThat(midiMessageMarshaller.toNote(message, tick)).isNotNull();
    }

    private ShortMessage createMessageWithCommand(int command) {
        ShortMessage message = mock(ShortMessage.class);
        when(message.getCommand()).thenReturn(command);
        return message;
    }
}
