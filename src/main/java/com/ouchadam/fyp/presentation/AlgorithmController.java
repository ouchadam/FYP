package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.GenerationCallback;
import com.ouchadam.fyp.algorithm.GeneticAlgorithm;
import com.ouchadam.fyp.algorithm.crossover.population.Evaluation;

import java.awt.*;

class AlgorithmController {

    public enum Status {
        IDLE, RUNNING;
    }

    private final GenerationController generationController;
    private final TextController textController;

    AlgorithmController(GenerationController generationController, TextController textController) {
        this.generationController = generationController;
        this.textController = textController;
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
        textController.setResultColour(Color.BLACK);
        generationController.setGenerationCallback(onGeneration);
        generationController.setOnFinish(onFinish);
        generationController.setHalter(halter);
        generationController.start();
        textController.setStartStop(Status.RUNNING);
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
            generationController.reset();
            if (evaluation.getResultType() == Evaluation.ResultType.PASS) {
                textController.setResultColour(Color.GREEN);
            } else {
                textController.setResultColour(Color.RED);
            }
            textController.setStartStop(Status.IDLE);
            halter.setHalted(false);
        }
    };

    private final GeneticAlgorithm.GenerationHalter halter = new GeneticAlgorithm.GenerationHalter() {

        private final static int GENERATION_LIMIT = 2000;
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

}
