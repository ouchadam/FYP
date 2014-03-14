package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.AlgorithmParams;
import com.ouchadam.fyp.algorithm.GenerationHalter;
import com.ouchadam.fyp.algorithm.Member;

import java.awt.*;

import com.ouchadam.fyp.presentation.midi.MemberToMidiSaver;
import org.junit.Test;
import org.mockito.Mock;

import helper.TestWithMocks;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlgorithmControllerTest extends TestWithMocks {

    @Mock GenerationController generationController;
    @Mock TextController textController;
    @Mock ParameterController parameterController;
    @Mock SequenceController sequenceController;
    @Mock RuleController ruleController;
    @Mock MemberToMidiSaver memberToMidiSaver;
    @Mock ResultManager resultManager;
    @Mock GenerationHalter halter;

    @Mock Component not_relevant;

    AlgorithmController algorithmController;
    private OnClickListener algorithmStartStopEntry;
    private OnClickListener algorithmSaveEntry;

    @Override
    protected void before() {
        algorithmController = new AlgorithmController(generationController, textController, parameterController, sequenceController, ruleController, memberToMidiSaver, resultManager, halter);
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

        verify(memberToMidiSaver).save(any(Member.class));
    }
}
