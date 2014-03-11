package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.*;
import com.ouchadam.fyp.algorithm.population.Evaluation;

class GenerationController {

    private final GenerationThread  generationThread;
    private final GeneticAlgorithmCreator geneticAlgorithmCreator;

    private GenerationCallback onGeneration;
    private GenerationHalter clientHalter;
    private OnFinish clientOnFinish;

    GenerationController(GenerationThread generationThread, GeneticAlgorithmCreator geneticAlgorithmCreator) {
        this.generationThread = generationThread;
        this.geneticAlgorithmCreator = geneticAlgorithmCreator;
    }

    public void start(AlgorithmParams params) {
        generationThread.start(algorithmRunner, params);
    }

    private final GenerationRunnable algorithmRunner = new GenerationRunnable() {
        @Override
        public void run(AlgorithmParams params) {
            clientHalter.setHalted(false);
            GeneticAlgorithm geneticAlgorithm = geneticAlgorithmCreator.create(internalCallback, params, internalHalter);
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
            reset();
            if (clientOnFinish != null) {
                clientOnFinish.onFinish(evaluation);
            }
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
