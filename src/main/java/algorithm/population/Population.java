package algorithm.population;

import java.util.List;

public class Population {

    private List<Member> offspring;

    public Population(List<Member> offspring) {
        this.offspring = offspring;
    }

    public int size() {
        return offspring.size();
    }
}
