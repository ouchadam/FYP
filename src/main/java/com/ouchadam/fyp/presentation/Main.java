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
            AlgorithmController algorithmController = new AlgorithmController(new GenerationController(new GenerationThread()), mainFrame, mainFrame);
            InteractionManager interactionManager = new InteractionManager(new MidiSelection(), mainFrame, mainFrame, mainFrame, algorithmController);
            mainFrame.setOpenMidiListener(interactionManager.openMidiListener());
            mainFrame.setAnaliseListener(interactionManager.analiseMidiListener());
            mainFrame.setStartStopListener(interactionManager.onStartStop());
            mainFrame.setSaveListener(interactionManager.onSave());
        }
    };

}

