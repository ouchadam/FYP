package com.ouchadam.fyp.presentation;

public class Entry {

    public static void main(String... args) {
        Entry entry = new Entry();
        entry.start();
    }

    private void start() {
        MainFrame mainFrame = MainFrame.newInstance();
        InteractionManager interactionManager = new InteractionManager(new MidiSelection(), mainFrame, mainFrame);
        mainFrame.setOpenMidiListener(interactionManager.openMidiListener());
        mainFrame.setAnaliseListener(interactionManager.analiseMidiListener());
    }

}

