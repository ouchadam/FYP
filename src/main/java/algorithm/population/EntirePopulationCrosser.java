package algorithm.population;

import algorithm.crossover.MemberCrossover;
import algorithm.crossover.RandomParentSelector;
import algorithm.crossover.UniformChromosomeCrossover;
import algorithm.gene.Chromosome;

import java.util.ArrayList;
import java.util.List;

public class EntirePopulationCrosser implements PopulationCrosser {

    private final MemberCrossover memberCrossover;

    public static EntirePopulationCrosser newInstance() {
        return new EntirePopulationCrosser(new MemberCrossover(new UniformChromosomeCrossover(new RandomParentSelector<Chromosome>())));
    }

    EntirePopulationCrosser(MemberCrossover memberCrossover) {
        this.memberCrossover = memberCrossover;
    }

    @Override
    public Population crossover(Population population) {
        List<Member> currentGeneration = population.getMembers();
        List<Member> newPopulation = new ArrayList<Member>();

        newPopulation.addAll(currentGeneration);
        for (int pairIndex = 0; pairIndex < population.size(); pairIndex = pairIndex+2) {
            newPopulation.add(memberCrossover.crossover(currentGeneration.get(pairIndex), currentGeneration.get(pairIndex + 1)));
        }
        return new Population(newPopulation);
    }

}
