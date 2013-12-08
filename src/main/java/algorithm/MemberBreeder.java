package algorithm;

public class MemberBreeder {

    private final Crossover crossover;

    public MemberBreeder(Crossover crossover) {
        this.crossover = crossover;
    }

    public Member createOffspring(Member parentX, Member parentY) {
        return crossover.crossover(parentX, parentY);
    }

    interface Crossover {
        Member crossover(Member parentX, Member parentY);
    }

}
