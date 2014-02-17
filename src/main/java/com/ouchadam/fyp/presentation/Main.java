package com.ouchadam.fyp.presentation;

public class Main {

    private MainFrame mainFrame;
    private InteractionManager interactionManager;

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
            interactionManager = new InteractionManager(new MidiSelection(), mainFrame, mainFrame);
            mainFrame.setOpenMidiListener(interactionManager.openMidiListener());
            mainFrame.setAnaliseListener(interactionManager.analiseMidiListener());
        }
    };

}

