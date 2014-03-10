package com.ouchadam.fyp.presentation;

import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import javax.swing.*;

import java.awt.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AlgorithmTabManagerTest extends TestWithMocks {

    @Mock SliderManager sliderManager;
    @Mock JButton startStop;
    @Mock JLabel textArea;
    @Mock JButton save;
    @Mock ClickManager clickManager;

    private AlgorithmTabManager algorithmTabManager;

    @Override
    protected void before() {
        algorithmTabManager = new AlgorithmTabManager(sliderManager, startStop, textArea, save, clickManager);
    }

    @Test
    public void delegate_crossover_to_slider_manager() throws Exception {
        algorithmTabManager.crossoverPercent();

        verify(sliderManager).get(SliderManager.SliderName.CROSSOVER_PERCENT);
    }

    @Test
    public void delegate_initialPopulation_to_slider_manager() throws Exception {
        algorithmTabManager.initialPopulation();

        verify(sliderManager).get(SliderManager.SliderName.INITIAL);
    }

    @Test
    public void delegate_maxPopulation_to_slider_manager() throws Exception {
        algorithmTabManager.maxPopulation();

        verify(sliderManager).get(SliderManager.SliderName.MAX);
    }

    @Test
    public void delegate_acceptableFitness_to_slider_manager() throws Exception {
        algorithmTabManager.acceptableFitness();

        verify(sliderManager).get(SliderManager.SliderName.ACCEPTABLE_FITNESS);
    }

    @Test
    public void delegate_mutationPercent_to_slider_manager() throws Exception {
        algorithmTabManager.mutationPercent();

        verify(sliderManager).get(SliderManager.SliderName.MUTATION_PERCENT);
    }

    @Test
    public void set_start_text_when_status_is_idle() throws Exception {
        algorithmTabManager.setStartStopText(AlgorithmController.Status.IDLE);

        verify(startStop).setText("Start");
    }

    @Test
    public void set_stop_text_when_status_is_running() throws Exception {
        algorithmTabManager.setStartStopText(AlgorithmController.Status.RUNNING);

        verify(startStop).setText("Stop");
    }

    @Test (expected = RuntimeException.class)
    public void throw_exception_when_unhandled_status_is_used() {
        algorithmTabManager.setStartStopText(null);
    }

    @Test
    public void set_the_start_stop_listener_on_the_correct_button() throws Exception {
        OnClickListener onClickListener = mock(OnClickListener.class);
        algorithmTabManager.setStartStopListener(onClickListener);

        verify(clickManager).setClickListener(startStop, onClickListener);
    }

    @Test
    public void set_the_save_listener_on_the_correct_button() throws Exception {
        OnClickListener onClickListener = mock(OnClickListener.class);
        algorithmTabManager.setSaveListener(onClickListener);

        verify(clickManager).setClickListener(save, onClickListener);
    }

    @Test
    public void enable_slider_manager_and_button() throws Exception {
        algorithmTabManager.enable();

        verify(sliderManager).enable();
        verify(save).setEnabled(true);
    }

    @Test
    public void disable_slider_manager_and_button() throws Exception {
        algorithmTabManager.disable();

        verify(sliderManager).disable();
        verify(save).setEnabled(false);
    }

    @Test
    public void testName() throws Exception {
        Color color = Color.BLACK;
        algorithmTabManager.setResultColour(color);

        verify(textArea).setForeground(color);

    }
}
