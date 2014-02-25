package com.ouchadam.fyp.presentation;

import java.awt.*;

public interface TextController {
    void setMidiSelection(String text);
    void appendGenerationText(String text);
    void setStartStop(InteractionManager.Foo startStopText);
    void setResultColour(Color colour);
}
