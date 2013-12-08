package algorithm.crossover;

import algorithm.Member;

public interface Crossover {
    Member crossover(Member parentX, Member parentY);
}
