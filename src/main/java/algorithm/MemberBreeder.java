package algorithm;

import algorithm.crossover.Crossover;

public class MemberBreeder {

    private final Crossover crossover;

    public MemberBreeder(Crossover crossover) {
        this.crossover = crossover;
    }

    public Member createOffspring(Member parentX, Member parentY) {
        return crossover.crossover(parentX, parentY);
    }

}
