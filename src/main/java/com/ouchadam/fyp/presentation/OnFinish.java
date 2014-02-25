package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.crossover.population.Evaluation;

interface OnFinish {
    void onFinish(Evaluation evaluation, ResultType resultType);

    enum ResultType {
        PASS, FAIL;
    }

}
