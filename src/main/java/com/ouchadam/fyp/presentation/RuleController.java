package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessRule;

import java.util.List;

public interface RuleController {
    List<FitnessRule<Member>> get();
}
