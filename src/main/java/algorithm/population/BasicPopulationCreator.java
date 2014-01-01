package algorithm.population;

import algorithm.crossover.Crossover;
import algorithm.crossover.SinglePointChromosomeCrossover;
import algorithm.crossover.SinglePointMemberCrossover;

import java.util.ArrayList;
import java.util.List;

public class BasicPopulationCreator implements PopulationCreator {

    public static final int POPULATION_SIZE = 10;

    private final MemberFactory memberFactory;

    public BasicPopulationCreator(MemberFactory memberFactory) {
        this.memberFactory = memberFactory;
    }

    @Override
    public Population create() {
        Member parentX = createParentMember();
        Member parentY = createParentMember();

        Crossover<Member> crossover = new SinglePointMemberCrossover(new SinglePointChromosomeCrossover(2));
        List<Member> newPopulation = new ArrayList<Member>(POPULATION_SIZE);
        addParentsToThePopulation(parentX, parentY, newPopulation);

        do {
            newPopulation.add(crossover.crossover(parentX, parentY));
        } while (newPopulation.size() < POPULATION_SIZE);

        return new Population(newPopulation);
    }

    private void addParentsToThePopulation(Member parentX, Member parentY, List<Member> newPopulation) {
        newPopulation.add(parentX);
        newPopulation.add(parentY);
    }

    private Member createParentMember() {
        return memberFactory.createRandomParentMember();
    }

}
