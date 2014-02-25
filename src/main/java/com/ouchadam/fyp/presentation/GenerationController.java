package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.GenerationCallback;
import com.ouchadam.fyp.algorithm.GeneticAlgorithm;
import com.ouchadam.fyp.algorithm.crossover.population.Evaluation;

class GenerationController {

    private Thread worker;

    private GenerationCallback onGeneration;
    private GeneticAlgorithm.GenerationHalter halter;
    private OnFinish onFinish;

    public void start() {
        if (worker != null) {
            stop();
        }
        this.worker = new Thread(algorithmRunner);
        worker.start();
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

    public void stop() {
        if (worker != null) {
            try {
                halter.setHalted(true);
                this.worker.join();
                this.worker = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

    public boolean isRunning() {
        return worker != null;
    }
}
