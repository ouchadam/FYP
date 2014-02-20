package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.GenerationCallback;
import com.ouchadam.fyp.algorithm.GeneticAlgorithm;
import com.ouchadam.fyp.algorithm.crossover.population.Evaluation;

class GenerationController {

    private GeneticAlgorithm geneticAlgorithm;
    private GenerationCallback onGeneration;

    public void start() {
        geneticAlgorithm = GeneticAlgorithm.newInstance(internalCallback, internalHalter);
        geneticAlgorithm.work();
    }

    private final GenerationCallback internalCallback = new GenerationCallback() {
        @Override
        public void onGeneration(Evaluation evaluation) {
            if (onGeneration != null) {
                onGeneration.onGeneration(evaluation);
            }
        }
    };

    private final GeneticAlgorithm.GenerationHalter internalHalter = new GeneticAlgorithm.GenerationHalter() {
        @Override
        public boolean halt(Evaluation evaluation, int index) {
            return false;
        }
    };

    public void setGenerationCallback(GenerationCallback onGeneration) {
        this.onGeneration = onGeneration;
    }
}
