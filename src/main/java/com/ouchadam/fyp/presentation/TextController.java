package com.ouchadam.fyp.presentation;

import java.awt.*;

public interface TextController {
    void appendGenerationText(String text);
    void setStartStop(AlgorithmController.Status startStopText);
    void setResultColour(Color colour);
}
