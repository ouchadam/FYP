package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.*;
import com.ouchadam.fyp.algorithm.population.Evaluation;
import com.ouchadam.fyp.analysis.Division;
import com.ouchadam.fyp.analysis.midi.Sequenced16thMidiNote;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import java.awt.*;
import java.util.List;

class AlgorithmController {

    public enum Status {
        IDLE, RUNNING
    }

    private final GenerationController generationController;
    private final TextController textController;
    private final ParameterController parameterController;
    private final RuleController ruleController;

    private Evaluation evaluation;

    AlgorithmController(GenerationController generationController, TextController textController, ParameterController parameterController, RuleController ruleController) {
        this.generationController = generationController;
        this.textController = textController;
        this.parameterController = parameterController;
        this.ruleController = ruleController;
    }

    public OnClickListener listener() {
        return onStartStop;
    }

    private final OnClickListener onStartStop = new OnClickListener() {
        @Override
        public void onClick(Component component) {
            Status status = generationController.status();
            switch (status) {
                case IDLE:
                    start();
                    break;
                case RUNNING:
                    stop();
                    break;
                default:
                    throw new IllegalArgumentException(status.name() + " is unhandled");
            }
        }
    };

    private void start() {
        parameterController.disable();
        textController.setResultColour(Color.BLACK);
        textController.setStartStop(Status.RUNNING);
        generationController.setGenerationCallback(onGeneration);
        generationController.setOnFinish(onFinish);
        generationController.setHalter(halter);
        generationController.start(getParams());
    }

    private AlgorithmParams getParams() {
        return new AlgorithmParams(parameterController.initialPopulation(),
                parameterController.maxPopulation(),
                parameterController.acceptableFitness(),
                parameterController.mutationPercent(),
                parameterController.crossoverPercent(),
                ruleController.get());
    }

    private final GenerationCallback onGeneration = new GenerationCallback() {
        @Override
        public void onGeneration(Evaluation evaluation, int generationIndex) {
            textController.appendGenerationText("Index : " + generationIndex + " Fitness : " + evaluation.fitnessValue(0).get());
            printBest(evaluation.population().get(0));
        }
    };

    private void printBest(Member bestMember) {
        for (RuleContainer<Member> rule : ruleController.get()) {
            System.out.println(rule.ruleName.name() + " " + rule.fitnessRule.apply(bestMember).get());
        }
    }

    private final OnFinish onFinish = new OnFinish() {
        @Override
        public void onFinish(Evaluation evaluation) {
            AlgorithmController.this.evaluation = evaluation;
            generationController.reset();
            if (evaluation.getResultType() == Evaluation.ResultType.PASS) {
                textController.setResultColour(Color.GREEN);
            } else {
                textController.setResultColour(Color.RED);
            }
            textController.setStartStop(Status.IDLE);
            halter.setHalted(false);
            parameterController.enable();
        }
    };

    private final GenerationHalter halter = new GenerationHalter() {

        private final static int GENERATION_LIMIT = 200000;
        private boolean isHalted = false;

        @Override
        public boolean isHalted(Evaluation evaluation, int index) {
            return index >= GENERATION_LIMIT || isHalted;
        }

        @Override
        public void setHalted(boolean halted) {
            this.isHalted = halted;
        }

    };

    private void stop() {
        generationController.stop();
        textController.setStartStop(Status.IDLE);
    }

    public OnClickListener onSave() {
        return onSave;
    }

    private final OnClickListener onSave = new OnClickListener() {
        @Override
        public void onClick(final Component component) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Member member = evaluation.population().get(0);
                    List<Sequenced16thMidiNote> notes = new MemberToMidi().convert(member);

                    try {
                        Sequence sequence = new Sequence(Division.PPQ.value(), 960, 1);
                        Track track = sequence.getTracks()[0];
                        track.add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, 20, 0), 0));
                        for (Sequenced16thMidiNote midiNote : notes) {
                            System.out.println("Adding : " + midiNote.getNote() + "(" + midiNote.getKey() + ")" + " at " + midiNote.getTick() + " : " + midiNote.getType() + " to : " + midiNote.getNoteOff().getTick());
                            track.add(midiNote.getNoteOn());
                            track.add(midiNote.getNoteOff());
                        }
                        new MidiPlayer().save(sequence, new MidiFileChooser(component, MidiFileChooser.Type.SAVE));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    };

}
