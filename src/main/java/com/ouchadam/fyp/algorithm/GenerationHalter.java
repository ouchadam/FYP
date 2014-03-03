package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.population.Evaluation;

public interface GenerationHalter {
    boolean isHalted(Evaluation evaluation, int index);
    void setHalted(boolean halted);
}
