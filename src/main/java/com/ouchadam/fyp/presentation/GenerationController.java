package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.AlgorithmParams;
import com.ouchadam.fyp.algorithm.GenerationCallback;
import com.ouchadam.fyp.algorithm.GenerationHalter;
import com.ouchadam.fyp.algorithm.GeneticAlgorithm;
import com.ouchadam.fyp.algorithm.population.Evaluation;

class GenerationController {

    private final GenerationThread  generationThread;

    private GenerationCallback onGeneration;
    private GenerationHalter halter;
    private OnFinish onFinish;

    GenerationController(GenerationThread generationThread) {
        this.generationThread = generationThread;
    }

    public void start(AlgorithmParams params) {
        generationThread.start(algorithmRunner, params);
    }

    private final GenerationRunnable algorithmRunner = new GenerationRunnable() {
        @Override
        public void run(AlgorithmParams params) {
            halter.setHalted(false);
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
            return halter != null ? halter.isHalted(evaluation, index) : false;
        }

        @Override
        public void setHalted(boolean halted) {
            if (halter != null) {
                halter.setHalted(halted);
            }
        }

    };

    private final OnFinish internalOnFinish = new OnFinish() {
        @Override
        public void onFinish(Evaluation evaluation) {
            System.out.println("Algorithm Finished");
            internalHalter.setHalted(false);
            if (onFinish != null) {
                onFinish.onFinish(evaluation);
            }
        }
    };

    public void reset() {
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
        this.halter = halter;
    }

    public void setOnFinish(OnFinish onFinish) {
        this.onFinish = onFinish;
    }

    public AlgorithmController.Status status() {
        return generationThread.isRunning() ? AlgorithmController.Status.RUNNING : AlgorithmController.Status.IDLE;
    }
}
