package com.ouchadam.fyp.presentation;

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
        public void onUiReady() {
            InteractionManager interactionManager = new InteractionManager(new MidiSelection(), mainFrame, mainFrame, AlgorithmController.from(mainFrame));
            mainFrame.setOpenMidiListener(interactionManager.openMidiListener());
            mainFrame.setStartStopListener(interactionManager.onStartStop());
            mainFrame.setSaveListener(interactionManager.onSave());
        }
    };

}

