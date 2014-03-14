package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.domain.Member;
import com.ouchadam.fyp.algorithm.population.Evaluation;

class ResultManager {

    private Evaluation evaluation;

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Member getBest() {
        return evaluation.population().get(0);
    }
}
