package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.MidiMessageMarshaller;
import com.ouchadam.fyp.analysis.MidiReader;

public class Main {

    private MainFrame mainFrame;

    public static void main(String... args) {
        Main main = new Main();
        main.start();
    }

    private void start() {
        mainFrame = MainFrame.newInstance(onUi);
    }

    private final UiReadyListener onUi = new UiReadyListener() {
        @Override
        public void onUiReady(FrameController frameController) {
            MidiReader midiReader = new MidiReader(new MidiMessageMarshaller(), new MidiSystemWrapper());
            InteractionManager interactionManager = new InteractionManager(new MidiSelection(), frameController, AlgorithmController.from(frameController), frameController, new DialogManager(), midiReader);
            frameController.setOpenMidiListener(interactionManager.openMidiListener());
            frameController.setStartStopListener(interactionManager.onStartStop());
            frameController.setSaveListener(interactionManager.onSave());
        }
    };

}

