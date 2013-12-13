package algorithm;

import algorithm.crossover.Crossover;

public class MemberBreeder {

    private final Crossover<Chromosome> crossover;

    public MemberBreeder(Crossover crossover) {
        this.crossover = crossover;
    }

    public Chromosome createOffspring(Chromosome parentX, Chromosome parentY) {
        return crossover.crossover(parentX, parentY);
    }

}
