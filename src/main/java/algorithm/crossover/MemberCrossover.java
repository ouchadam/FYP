package algorithm.crossover;

import algorithm.gene.Chromosome;
import algorithm.population.ChromosomeManager;
import algorithm.population.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberCrossover implements Crossover<Member> {

    private final Crossover<Chromosome> chromosomeCrossover;

    public MemberCrossover(Crossover<Chromosome> chromosomeCrossover) {
        this.chromosomeCrossover = chromosomeCrossover;
    }

    @Override
    public Member crossover(Member parentX, Member parentY) {
        List<Chromosome> chromosomeList = new ArrayList<Chromosome>(parentX.count());
        for (int index = 0; index < parentX.count(); index++) {
            chromosomeList.add(chromosomeCrossover.crossover(parentX.get(index), parentY.get(index)));
        }
        return new Member(ChromosomeManager.from(chromosomeList));
    }

}
