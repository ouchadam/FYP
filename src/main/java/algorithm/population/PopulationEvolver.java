package algorithm.population;

import java.util.List;

public class PopulationEvolver {

    private final PopulationMutator populationMutator;
    private final PopulationCrosser populationCrosser;
    private final FittestMemberFinder fittestMemberFinder;

    public PopulationEvolver(PopulationMutator populationMutator, PopulationCrosser populationCrosser, FittestMemberFinder fittestMemberFinder) {
        this.populationMutator = populationMutator;
        this.populationCrosser = populationCrosser;
        this.fittestMemberFinder = fittestMemberFinder;
    }

    public Population evolve(Population initialPopulation) {
        return rec(initialPopulation, 0);
    }

    private Population rec(Population seed, int count) {

        // TODO population should be small, contain the best of the previous generation?
        // TODO this should then seed a new population (possibly add to previous?)  via crossover
        // TODO perform mutation
        // TODO select best individuals are start again  - breeding technique

        Population population = populationCrosser.crossover(seed);
        Population mutatedPopulation = populationMutator.mutate(population);
        List<Member> members = fittestMemberFinder.find(mutatedPopulation, 4);
        Population fittestPopulationMembers = new Population(members);
        return count < 100 ? rec(fittestPopulationMembers, ++count) : fittestPopulationMembers;
    }

}
