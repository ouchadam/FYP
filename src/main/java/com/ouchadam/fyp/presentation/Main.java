package com.ouchadam.fyp.presentation;

public class Main {

    public static void main(String... args) {
        Main main = new Main();
        main.start();
    }

    private void start() {
        MainFrame mainFrame = MainFrame.newInstance();
        InteractionManager interactionManager = new InteractionManager(new MidiSelection(), mainFrame, mainFrame);
        mainFrame.setOpenMidiListener(interactionManager.openMidiListener());
        mainFrame.setAnaliseListener(interactionManager.analiseMidiListener());
    }

}

