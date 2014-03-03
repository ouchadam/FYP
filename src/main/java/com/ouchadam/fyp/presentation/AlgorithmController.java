package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.*;
import com.ouchadam.fyp.algorithm.population.Evaluation;
import com.ouchadam.fyp.analysis.Division;
import com.ouchadam.fyp.analysis.midi.Sequenced16thMidiNote;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


class AlgorithmController {

    public enum Status {
        IDLE, RUNNING;

    }

    private final GenerationController generationController;
    private final TextController textController;
    private final ParameterController parameterController;

    private Evaluation evaluation;

    AlgorithmController(GenerationController generationController, TextController textController, ParameterController parameterController) {
        this.generationController = generationController;
        this.textController = textController;
        this.parameterController = parameterController;
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
        return new AlgorithmParams(parameterController.initialPopulation(), parameterController.maxPopulation(), parameterController.acceptableFitness());
    }

    private final GenerationCallback onGeneration = new GenerationCallback() {
        @Override
        public void onGeneration(Evaluation evaluation, int generationIndex) {
            textController.appendGenerationText("Index : " + generationIndex + " Fitness : " + evaluation.fitnessValue(0).get());
        }
    };

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

        private final static int GENERATION_LIMIT = 20000;
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
        public void onClick(Component component) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Member member = evaluation.population().get(0);
                    List<Sequenced16thMidiNote> notes = new MemberToMidi().convert(member);
                    new MidiPlayer().play(notes, Division.PPQ, 960);
                }
            }).start();
        }
    };

    private static class MemberToMidi {

        private List<Sequenced16thMidiNote> sequencedMidiNotes;

        public List<Sequenced16thMidiNote> convert(Member member) {
            sequencedMidiNotes = new ArrayList<Sequenced16thMidiNote>(member.size());
            member.forEvery(onEvery);
            return sequencedMidiNotes;
        }

        private final ForEvery<Integer, Note, Void> onEvery = new ForEvery<Integer, Note, Void>() {
            @Override
            public void on(Integer position, Note note, Void bar) {
                sequencedMidiNotes.add(Sequenced16thMidiNote.from(position, 1, note.decimal(), 0x60, 960));
            }
        };
    }

}
