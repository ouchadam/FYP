package com.ouchadam.fyp.presentation;

import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import java.awt.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlgorithmControllerTest extends TestWithMocks {

    @Mock GenerationController generationController;
    @Mock TextController textController;

    @Mock Component not_relevant;

    AlgorithmController algorithmController;
    private OnClickListener algorithmEntry;

    @Override
    protected void before() {
        algorithmController = new AlgorithmController(generationController, textController);
        algorithmEntry = algorithmController.listener();

    }

    @Test
    public void when_the_algorithm_is_running_clicking_startstop_should_trigger_stop() {
        when(generationController.status()).thenReturn(AlgorithmController.Status.RUNNING);

        algorithmEntry.onClick(not_relevant);

        verify(generationController).stop();
    }


    @Test
    public void when_the_algorithm_is_idle_clicking_startstop_should_trigger_start() {
        when(generationController.status()).thenReturn(AlgorithmController.Status.IDLE);

        algorithmEntry.onClick(not_relevant);

        verify(generationController).start();
    }

}
