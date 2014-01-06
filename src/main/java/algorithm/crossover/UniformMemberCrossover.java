package algorithm.crossover;

import algorithm.fitness.MemberFitness;
import algorithm.gene.Chromosome;
import algorithm.population.ChromosomeManager;
import algorithm.population.Member;

import java.util.ArrayList;
import java.util.List;

public class UniformMemberCrossover implements Crossover<Member> {

    private final UniformChromosomeCrossover singlePointChromosomeCrossover;

    public UniformMemberCrossover(UniformChromosomeCrossover singlePointChromosomeCrossover) {
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
