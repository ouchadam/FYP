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
        public void onUiReady(FrameController frameController) {
            InteractionManager interactionManager = new InteractionManager(new MidiSelection(), frameController, AlgorithmController.from(frameController), frameController);
            frameController.setOpenMidiListener(interactionManager.openMidiListener());
            frameController.setStartStopListener(interactionManager.onStartStop());
            frameController.setSaveListener(interactionManager.onSave());
        }
    };

}

