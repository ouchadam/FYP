package com.ouchadam.fyp.presentation;

import org.junit.Test;
import org.mockito.Mock;

import helper.TestWithMocks;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class GenerationControllerTest extends TestWithMocks {

    @Mock
    GenerationThread generationThread;
    private GenerationController generationController;

    @Override
    protected void before() {
        generationController = new GenerationController(generationThread);
    }

    @Test
    public void start_the_worker_thread() throws Exception {
        generationController.start();

        verify(generationThread).start(any(Runnable.class));
    }

    @Test
    public void stop_the_worker_thread() throws Exception {
        generationController.stop();

        verify(generationThread).stop();
    }

    @Test
    public void not_explode_with_a_null_halter() throws Exception {
        generationController.setHalter(null);

        generationController.stop();
    }

}
