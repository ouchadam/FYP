package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.analysis.MidiTrack;

import javax.swing.*;
import java.awt.*;
import java.io.File;

class FrameController implements TextController, SequenceController, ParameterController, RuleController, FileSelectionProvider {

    private final SequenceTabCreator sequenceTabManager;
    private final AlgorithmTabManager algorithmTabManager;
    private final RuleTabCreator ruleTabManager;
    private final JFrame frame;

    FrameController(SequenceTabCreator sequenceTabManager, AlgorithmTabManager algorithmTabManager, RuleTabCreator ruleTabManager, JFrame frame) {
        this.sequenceTabManager = sequenceTabManager;
        this.algorithmTabManager = algorithmTabManager;
        this.ruleTabManager = ruleTabManager;
        this.frame = frame;
    }

    public void setOpenMidiListener(OnClickListener listener) {
        sequenceTabManager.setOpenMidiListener(listener);
    }

    public void setSaveListener(OnClickListener onClickListener) {
        algorithmTabManager.setSaveListener(onClickListener);
    }

    public void setAnalyseListener(OnClickListener onClickListener) {
        algorithmTabManager.setAnalyseListener(onClickListener);
    }

    @Override
    public void appendGenerationText(String text) {
        algorithmTabManager.updateText(text);
    }

    @Override
    public void setStartStop(AlgorithmController.Status startStopText) {
        algorithmTabManager.setStartStopText(startStopText);
    }

    @Override
    public void setResultColour(Color colour) {
        algorithmTabManager.setResultColour(colour);
    }

    @Override
    public void open(MidiTrack midiTrack) {
        sequenceTabManager.open(midiTrack);
    }

    @Override
    public void analyse(File file) {
        sequenceTabManager.analyse(file);
    }

    public void setStartStopListener(OnClickListener onClickListener) {
        algorithmTabManager.setStartStopListener(onClickListener);
    }

    @Override
    public int initialPopulation() {
        return algorithmTabManager.initialPopulation();
    }

    @Override
    public int maxPopulation() {
        return algorithmTabManager.maxPopulation();
    }

    @Override
    public int acceptableFitness() {
        return algorithmTabManager.acceptableFitness();
    }

    @Override
    public int mutationPercent() {
        return algorithmTabManager.mutationPercent();
    }

    @Override
    public int crossoverPercent() {
        return algorithmTabManager.crossoverPercent();
    }

    @Override
    public void enable() {
        algorithmTabManager.enable();
    }

    @Override
    public void disable() {
        algorithmTabManager.disable();
    }

    @Override
    public java.util.List<RuleContainer<Member>> get() {
        return ruleTabManager.get();
    }

    @Override
    public MidiFileChooser getFileChooser(MidiFileChooser.Type type) {
        return new MidiFileChooser(frame, new MidiChooser().createFileChooser(), type);
    }

}
