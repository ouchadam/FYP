package algorithm.crossover;

import algorithm.gene.Chromosome;
import algorithm.gene.Feature;
import algorithm.gene.Gene;
import algorithm.gene.GeneManager;
import algorithm.population.ChromosomeManager;
import algorithm.population.Member;

import java.util.ArrayList;
import java.util.List;

public class SinglePointMemberCrossover implements Crossover<Member> {

    private final SinglePointChromosomeCrossover singlePointChromosomeCrossover;

    public SinglePointMemberCrossover(SinglePointChromosomeCrossover singlePointChromosomeCrossover) {
        this.singlePointChromosomeCrossover = singlePointChromosomeCrossover;
    }

    @Override
    public Member crossover(Member parentX, Member parentY) {
        List<Chromosome> chromosomeList = new ArrayList<Chromosome>(parentX.count());
        for (int index = 0; index < parentX.count(); index++) {
            chromosomeList.add(singlePointChromosomeCrossover.crossover(parentX.get(index), parentY.get(index)));
        }
        return new Member(ChromosomeManager.from(chromosomeList));
    }

}
