package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.MidiTrack;
import com.ouchadam.fyp.presentation.tab.AlgorithmTabManager;
import com.ouchadam.fyp.presentation.tab.RuleTabCreator;
import com.ouchadam.fyp.presentation.tab.SequenceTabCreator;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import javax.swing.*;

import java.awt.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FrameControllerTest extends TestWithMocks {

    @Mock SequenceTabCreator sequenceTabManager;
    @Mock AlgorithmTabManager algorithmTabManager;
    @Mock RuleTabCreator ruleTabManager;
    @Mock JFrame frame;

    FrameController frameController;

    @Override
    protected void before() {
        frameController = new FrameController(sequenceTabManager, algorithmTabManager, ruleTabManager, frame);
    }

    @Test
    public void delegate_generation_update_text_to_algorithm_tab() throws Exception {
        String text = "generation text";
        frameController.appendGenerationText(text);

        verify(algorithmTabManager).updateText(text);
    }

    @Test
    public void delegate_start_stop_text_to_algorithm_tab() throws Exception {
        AlgorithmController.Status status = AlgorithmController.Status.IDLE;
        frameController.setStartStop(status);

        verify(algorithmTabManager).setStartStopText(status);
    }


    @Test
    public void delegate_setting_the_result_colour_to_algorithm_tab() throws Exception {
        Color color = Color.BLACK;
        frameController.setResultColour(color);

        verify(algorithmTabManager).setResultColour(color);
    }

    @Test
    public void delegate_opening_miditracks_to_the_sequence_tab() throws Exception {
        MidiTrack midiTrack = mock(MidiTrack.class);
        frameController.open(midiTrack);

        verify(sequenceTabManager).open(midiTrack);
    }

    @Test
    public void delegate_start_stop_listener_to_the_algorithm_tab() throws Exception {
        OnClickListener startStopListener = mock(OnClickListener.class);
        frameController.setStartStopListener(startStopListener);

        verify(algorithmTabManager).setStartStopListener(startStopListener);
    }

    @Test
    public void delegate_initial_population_param_to_the_algorithm_tab() throws Exception {
        frameController.initialPopulation();

        verify(algorithmTabManager).initialPopulation();
    }

    @Test
    public void delegate_max_population_param_to_the_algorithm_tab() throws Exception {
        frameController.maxPopulation();

        verify(algorithmTabManager).maxPopulation();
    }

    @Test
    public void delegate_acceptable_fitness_param_to_the_algorithm_tab() throws Exception {
        frameController.acceptableFitness();

        verify(algorithmTabManager).acceptableFitness();
    }

    @Test
    public void delegate_mutation_percent_param_to_the_algorithm_tab() throws Exception {
        frameController.mutationPercent();

        verify(algorithmTabManager).mutationPercent();
    }

    @Test
    public void delegate_crossover_percent_param_to_the_algorithm_tab() throws Exception {
        frameController.crossoverPercent();

        verify(algorithmTabManager).crossoverPercent();
    }

    @Test
    public void delegate_ui_enabling_to_the_algorithm_tab() throws Exception {
        frameController.enable();

        verify(algorithmTabManager).enable();
    }

    @Test
    public void delegate_ui_disabling_to_the_algorithm_tab() throws Exception {
        frameController.disable();

        verify(algorithmTabManager).disable();
    }

    @Test
    public void delegate_rule_container_get_to_the_rule_tab() throws Exception {
        frameController.get();

        verify(ruleTabManager).get();
    }

}
