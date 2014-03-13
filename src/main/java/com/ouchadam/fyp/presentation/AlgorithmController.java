package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.*;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;
import com.ouchadam.fyp.algorithm.population.Evaluation;

import java.awt.*;

class AlgorithmController {

    public enum Status {
        IDLE, RUNNING
    }

    private final GenerationController generationController;
    private final TextController textController;
    private final ParameterController parameterController;
    private final SequenceController sequenceController;
    private final RuleController ruleController;
    private final MemberToMidiSaver memberToMidiSaver;
    private final ResultManager resultManager;
    private final GenerationHalter halter;

    public static AlgorithmController from(FrameController frameController) {
        MemberToMidiSaver memberToMidiSaver = new MemberToMidiSaver(frameController.getFileChooser(MidiFileChooser.Type.SAVE), new MidiSystemWrapper(), new MemberToSequence(new MemberToMidi()));
        GenerationController generationController = new GenerationController(new GenerationThread(), GeneticAlgorithmCreator.newInstance());
        return new AlgorithmController(generationController, frameController, frameController, frameController, frameController, memberToMidiSaver, new ResultManager(), new FooHalter());
    }

    AlgorithmController(GenerationController generationController, TextController textController, ParameterController parameterController,
                        SequenceController sequenceController, RuleController ruleController, MemberToMidiSaver memberToMidiSaver, ResultManager resultManager, GenerationHalter halter) {
        this.generationController = generationController;
        this.textController = textController;
        this.parameterController = parameterController;
        this.sequenceController = sequenceController;
        this.ruleController = ruleController;
        this.memberToMidiSaver = memberToMidiSaver;
        this.resultManager = resultManager;
        this.halter = halter;
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
        generationController.setClientOnFinish(onFinish);
        generationController.setHalter(halter);
        generationController.start(getParams());
    }

    private AlgorithmParams getParams() {
        return AlgorithmParams.from(parameterController, ruleController);
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
            handleFinishUi(evaluation);

            System.out.println("-- Algorithm Finished --");
            System.out.println("");
            print(evaluation.population().get(0));

            for (RuleContainer<Member> memberRuleContainer : getParams().getRules()) {
                FitnessValue apply = memberRuleContainer.fitnessRule.apply(evaluation.population().get(0));
                System.out.println(memberRuleContainer.ruleName + " : " + apply + " weighted : " + apply.weight(memberRuleContainer.weight) + " for weight : " + memberRuleContainer.weight);
            }

            KeyAnalysis keyAnalysis = new KeyAnalysis(new ScaleCreator());
            System.out.println(keyAnalysis.apply(new MemberToMidi().convert(evaluation.population().get(0))));

        }
    };

    private static void print(Member member) {
        for (NoteValue noteValue : member.all().noteValues()) {
            System.out.println(noteValue.decimal());
        }
    }

    private void handleFinishUi(Evaluation evaluation) {
        switch (evaluation.getResultType()) {
            case PASS:
                textController.setResultColour(Color.GREEN);
                break;

            default:
                textController.setResultColour(Color.RED);
                break;
        }
        textController.setStartStop(Status.IDLE);
        parameterController.enable();
    }

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
            memberToMidiSaver.save(resultManager.getBest());
        }
    };

    public OnClickListener onAnalyse() {
        return onAnalyse;
    }

    private final OnClickListener onAnalyse = new OnClickListener() {
        @Override
        public void onClick(final Component component) {
            sequenceController.analyse(memberToMidiSaver.asFile(resultManager.getBest()));
        }
    };

}
