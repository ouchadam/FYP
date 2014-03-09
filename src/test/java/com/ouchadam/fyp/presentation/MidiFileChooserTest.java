package com.ouchadam.fyp.presentation;

import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MidiFileChooserTest extends TestWithMocks {

    @Mock MidiFileChooser.FileChooserResult result;
    @Mock JFileChooser fileChooser;
    @Mock Component component;

    @Test
    public void callback_with_cancel_when_the_file_choser_is_cancelled() throws Exception {
        when(fileChooser.showOpenDialog(component)).thenReturn(JFileChooser.CANCEL_OPTION);

        MidiFileChooser midiFileChooser = new MidiFileChooser(component, fileChooser, MidiFileChooser.Type.OPEN);
        midiFileChooser.choose(result);

        verify(result).onCancel();
    }

    @Test
    public void callback_with_result_when_file_is_chosen() throws Exception {
        File selectionResult = new File("/");
        when(fileChooser.getSelectedFile()).thenReturn(selectionResult);
        when(fileChooser.showOpenDialog(component)).thenReturn(JFileChooser.APPROVE_OPTION);

        MidiFileChooser midiFileChooser = new MidiFileChooser(component, fileChooser, MidiFileChooser.Type.OPEN);
        midiFileChooser.choose(result);

        verify(result).onSelection(selectionResult);
    }

    @Test
    public void use_the_open_dialog_for_opening() throws Exception {
        MidiFileChooser midiFileChooser = new MidiFileChooser(component, fileChooser, MidiFileChooser.Type.OPEN);

        midiFileChooser.choose(result);

        verify(fileChooser).showOpenDialog(component);
    }

    @Test
    public void use_the_save_dialog_for_saving() throws Exception {
        when(fileChooser.getSelectedFile()).thenReturn(new File("/"));
        MidiFileChooser midiFileChooser = new MidiFileChooser(component, fileChooser, MidiFileChooser.Type.SAVE);

        midiFileChooser.choose(result);

        verify(fileChooser).showSaveDialog(component);
    }

    @Test (expected = RuntimeException.class)
    public void throw_exception_when_unhandled_type_is_used() throws Exception {
        MidiFileChooser.Type unhandledType = null;
        MidiFileChooser midiFileChooser = new MidiFileChooser(component, fileChooser, unhandledType);

        midiFileChooser.choose(result);
    }
}
