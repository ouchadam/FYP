package algorithm.population;

import algorithm.crossover.Crossover;
import algorithm.crossover.MemberCrossover;
import algorithm.crossover.SinglePointChromosomeCrossover;

import java.util.ArrayList;
import java.util.List;

public class BasicPopulationCreator implements PopulationCreator {

    public static final int POPULATION_SIZE = 10;

    private final MemberFactory memberFactory;
    private final Crossover<Member> memberCrossover;

    public static BasicPopulationCreator newInstance() {
        return new BasicPopulationCreator(new MemberFactory(), new MemberCrossover(new SinglePointChromosomeCrossover(2)));
    }

    BasicPopulationCreator(MemberFactory memberFactory, Crossover<Member> memberCrossover) {
        this.memberFactory = memberFactory;
        this.memberCrossover = memberCrossover;
    }

    @Override
    public Population create() {
        Member parentX = createParentMember();
        Member parentY = createParentMember();

        List<Member> newPopulation = new ArrayList<Member>(POPULATION_SIZE);
        addParentsToThePopulation(parentX, parentY, newPopulation);

        do {
            newPopulation.add(memberCrossover.crossover(parentX, parentY));
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
