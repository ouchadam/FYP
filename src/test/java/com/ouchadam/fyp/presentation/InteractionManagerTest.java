package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.MidiReader;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.awt.*;
import java.io.File;

import static org.mockito.Mockito.*;

public class InteractionManagerTest extends TestWithMocks {

    @Mock MidiFileChooser midiFileChooser;
    @Mock DialogManager dialogManager;
    @Mock MidiSelection midiSelection;
    @Mock MidiReader midiReader;
    @Mock SequenceController sequenceController;
    @Mock AlgorithmController algorithmController;
    @Mock FileSelectionProvider fileSelectionProvider;

    private InteractionManager interactionManager;

    @Override
    protected void before() {
        when(fileSelectionProvider.getFileChooser(MidiFileChooser.Type.OPEN)).thenReturn(midiFileChooser);
        interactionManager = new InteractionManager(midiSelection, sequenceController, algorithmController, fileSelectionProvider, dialogManager, midiReader);
    }

    @Test
    public void delegate_save_listener_to_algorithm_controller() {
        interactionManager.onSave();

        verify(algorithmController).onSave();
    }

    @Test
    public void delegate_start_stop_listener_to_algorithm_controller() {
        interactionManager.onStartStop();

        verify(algorithmController).startStopListener();
    }

    @Test
    public void show_dialog_when_there_is_no_midi_selection() {
        when(midiSelection.hasMidiFile()).thenReturn(false);

        interactionManager.openMidiListener().onClick(null);

        verify(dialogManager).showMessageDialog(any(Component.class), anyString());
    }

    @Test
    public void read_midi_when_selected() throws Exception {
        when(midiSelection.hasMidiFile()).thenReturn(true);

        interactionManager.openMidiListener().onClick(null);

        verify(midiReader).read(any(MidiSelection.class));
    }

    @Test
    public void set_midi_selection_on_file_choosen() throws Exception {
        FileChooserWrapper fileChooserWrapper = new FileChooserWrapper();
        getFileChooser(fileChooserWrapper);

        File midiFile = new File("midi file");
        fileChooserWrapper.fileChooserResult.onSelection(midiFile);

        verify(midiSelection).setMidiFile(midiFile);
    }

    @Test
    public void do_nothing_when_selection_is_cancelled() throws Exception {
        FileChooserWrapper fileChooserWrapper = new FileChooserWrapper();
        getFileChooser(fileChooserWrapper);

        fileChooserWrapper.fileChooserResult.onCancel();

        verify(midiSelection, never()).setMidiFile(any(File.class));
    }

    private void getFileChooser(final FileChooserWrapper fileChooserWrapper) {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                fileChooserWrapper.fileChooserResult = (MidiFileChooser.FileChooserResult) invocation.getArguments()[0];
                return null;
            }
        }).when(midiFileChooser).choose(any(MidiFileChooser.FileChooserResult.class));
        interactionManager.openMidiListener().onClick(null);
    }

    private static class FileChooserWrapper {
        MidiFileChooser.FileChooserResult fileChooserResult = null;
    }
}
