package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.GenerationHalter;
import com.ouchadam.fyp.algorithm.population.Evaluation;

class FooHalter implements GenerationHalter {

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

}
