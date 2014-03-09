package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.*;
import com.ouchadam.fyp.algorithm.population.Evaluation;

class GenerationController {

    private final GenerationThread  generationThread;

    private GenerationCallback onGeneration;
    private GenerationHalter clientHalter;
    private OnFinish clientOnFinish;

    GenerationController(GenerationThread generationThread) {
        this.generationThread = generationThread;
    }

    public void start(AlgorithmParams params) {
        generationThread.start(algorithmRunner, params);
    }

    private final GenerationRunnable algorithmRunner = new GenerationRunnable() {
        @Override
        public void run(AlgorithmParams params) {
            clientHalter.setHalted(false);
            GeneticAlgorithm geneticAlgorithm = GeneticAlgorithm.newInstance(internalCallback, params, internalHalter);
            internalOnFinish.onFinish(geneticAlgorithm.work());
        }
    };

    private final GenerationCallback internalCallback = new GenerationCallback() {
        @Override
        public void onGeneration(Evaluation evaluation, int generationIndex) {
            if (onGeneration != null) {
                onGeneration.onGeneration(evaluation, generationIndex);
            }
        }
    };

    private final GenerationHalter internalHalter = new GenerationHalter() {

        @Override
        public boolean isHalted(Evaluation evaluation, int index) {
            return clientHalter != null ? clientHalter.isHalted(evaluation, index) : false;
        }

        @Override
        public void setHalted(boolean halted) {
            if (clientHalter != null) {
                clientHalter.setHalted(halted);
            }
        }

    };

    private final OnFinish internalOnFinish = new OnFinish() {
        @Override
        public void onFinish(Evaluation evaluation) {
            System.out.println("Algorithm Finished");
            print(evaluation.population().get(0));

            KeyAnalysis keyAnalysis = new KeyAnalysis(new ScaleCreator());
            KeyAnalysis.Result keyResult = keyAnalysis.analyse(new MemberToMidi().convert(evaluation.population().get(0)));

            System.out.println("Key Likelyhood : " + keyResult.key + " " + keyResult.type + " " + keyResult.percent + "%");

            reset();
            if (clientOnFinish != null) {
                clientOnFinish.onFinish(evaluation);
            }
        }
    };

    public static void print(Member member) {
        member.forEach().note(printNote);
    }

    private final static ForEach<NoteValue> printNote = new ForEach<NoteValue>() {
        @Override
        public void on(NoteValue what) {
            System.out.println(what.decimal());
        }
    };

    private void reset() {
        internalHalter.setHalted(false);
        generationThread.reset();
    }

    public void stop() {
        internalHalter.setHalted(true);
        generationThread.stop();
    }

    public void setGenerationCallback(GenerationCallback onGeneration) {
        this.onGeneration = onGeneration;
    }

    public void setHalter(GenerationHalter halter) {
        this.clientHalter = halter;
    }

    public void setClientOnFinish(OnFinish clientOnFinish) {
        this.clientOnFinish = clientOnFinish;
    }

    public AlgorithmController.Status status() {
        return generationThread.isRunning() ? AlgorithmController.Status.RUNNING : AlgorithmController.Status.IDLE;
    }
}
