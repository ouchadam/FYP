package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.GenerationCallback;
import com.ouchadam.fyp.algorithm.GeneticAlgorithm;
import com.ouchadam.fyp.algorithm.crossover.population.Evaluation;

class GenerationController {

    private final GenerationThread  generationThread;

    private GenerationCallback onGeneration;
    private GeneticAlgorithm.GenerationHalter halter;
    private OnFinish onFinish;

    GenerationController(GenerationThread generationThread) {
        this.generationThread = generationThread;
    }

    public void start() {
        generationThread.start(algorithmRunner);
    }

    private final Runnable algorithmRunner = new Runnable() {
        @Override
        public void run() {
            halter.setHalted(false);
            GeneticAlgorithm geneticAlgorithm = GeneticAlgorithm.newInstance(internalCallback, internalHalter);
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

    private final GeneticAlgorithm.GenerationHalter internalHalter = new GeneticAlgorithm.GenerationHalter() {

        @Override
        public boolean isHalted(Evaluation evaluation, int index) {
            return halter != null ? halter.isHalted(evaluation, index) : false;
        }

        @Override
        public void setHalted(boolean halted) {}

    };

    private final OnFinish internalOnFinish = new OnFinish() {
        @Override
        public void onFinish(Evaluation evaluation) {
            halter.setHalted(false);
            if (onFinish != null) {
                onFinish.onFinish(evaluation);
            }
        }
    };

    public void reset() {
        halter.setHalted(false);
        generationThread.reset();
    }

    public void stop() {
        internalHalter.setHalted(true);
        generationThread.stop();
    }

    public void setGenerationCallback(GenerationCallback onGeneration) {
        this.onGeneration = onGeneration;
    }

    public void setHalter(GeneticAlgorithm.GenerationHalter halter) {
        this.halter = halter;
    }

    public void setOnFinish(OnFinish onFinish) {
        this.onFinish = onFinish;
    }

    public AlgorithmController.Status status() {
        return generationThread.isRunning() ? AlgorithmController.Status.RUNNING : AlgorithmController.Status.IDLE;
    }
}
