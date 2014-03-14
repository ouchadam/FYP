package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.domain.Member;
import com.ouchadam.fyp.algorithm.population.Population;

class Tournament {

    public Member tournament(Population population, Member memberOne, Member memberTwo) {
        return population.indexOf(memberOne) < population.indexOf(memberTwo) ? memberOne : memberTwo;
    }

}
