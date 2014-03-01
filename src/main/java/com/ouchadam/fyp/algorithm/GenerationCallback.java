package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.population.Evaluation;

public interface GenerationCallback {
    void onGeneration(Evaluation evaluation, int generationIndex);
}
