package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.population.Evaluation;

interface GenerationCallback {
    void onGeneration(Evaluation evaluation);
}
