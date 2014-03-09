package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.*;
import com.ouchadam.fyp.algorithm.population.Evaluation;

import java.awt.*;

class AlgorithmController {

    public enum Status {
        IDLE, RUNNING
    }

    private final GenerationController generationController;
    private final TextController textController;
    private final ParameterController parameterController;
    private final RuleController ruleController;
    private final MemberToMidiFile memberToMidiFile;
    private final ResultManager resultManager;

    public static AlgorithmController from(MainFrame mainFrame) {
        MemberToMidiFile memberToMidiFile = new MemberToMidiFile(mainFrame.getFileChooser(MidiFileChooser.Type.SAVE));
        return new AlgorithmController(new GenerationController(new GenerationThread()), mainFrame, mainFrame, mainFrame, memberToMidiFile, new ResultManager());
    }

    AlgorithmController(GenerationController generationController, TextController textController, ParameterController parameterController, RuleController ruleController, MemberToMidiFile memberToMidiFile, ResultManager resultManager) {
        this.generationController = generationController;
        this.textController = textController;
        this.parameterController = parameterController;
        this.ruleController = ruleController;
        this.memberToMidiFile = memberToMidiFile;
        this.resultManager = resultManager;
    }

    public OnClickListener startStopListener() {
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
            resultManager.setEvaluation(evaluation);
            generationController.reset();

            switch (evaluation.getResultType()) {
                case PASS:
                    textController.setResultColour(Color.GREEN);
                    break;

                default:
                    textController.setResultColour(Color.RED);
                    break;
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
            memberToMidiFile.save(resultManager.getBest());
        }
    };

}
