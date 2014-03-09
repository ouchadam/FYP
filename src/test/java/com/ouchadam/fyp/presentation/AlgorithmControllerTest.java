package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.AlgorithmParams;
import com.ouchadam.fyp.algorithm.GenerationHalter;
import com.ouchadam.fyp.algorithm.Member;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import java.awt.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlgorithmControllerTest extends TestWithMocks {

    @Mock GenerationController generationController;
    @Mock TextController textController;
    @Mock ParameterController parameterController;
    @Mock RuleController ruleController;
    @Mock MemberToMidiFile memberToMidiFile;
    @Mock ResultManager resultManager;
    @Mock GenerationHalter halter;

    @Mock Component not_relevant;

    AlgorithmController algorithmController;
    private OnClickListener algorithmStartStopEntry;
    private OnClickListener algorithmSaveEntry;

    @Override
    protected void before() {
        algorithmController = new AlgorithmController(generationController, textController, parameterController, ruleController, memberToMidiFile, resultManager, halter);
        algorithmStartStopEntry = algorithmController.startStopListener();
        algorithmSaveEntry = algorithmController.onSave();
    }

    @Test
    public void when_the_algorithm_is_running_clicking_startstop_should_trigger_stop() {
        when(generationController.status()).thenReturn(AlgorithmController.Status.RUNNING);

        algorithmStartStopEntry.onClick(not_relevant);

        verify(generationController).stop();
    }

    @Test
    public void when_the_algorithm_is_idle_clicking_startstop_should_trigger_start() {
        when(generationController.status()).thenReturn(AlgorithmController.Status.IDLE);

        algorithmStartStopEntry.onClick(not_relevant);

        verify(generationController).start(any(AlgorithmParams.class));
    }

    @Test
    public void clicking_save_triggers_the_member_to_midi_process() {
        algorithmSaveEntry.onClick(not_relevant);

        verify(memberToMidiFile).save(any(Member.class));
    }
}
