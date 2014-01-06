package algorithm.population;

import algorithm.fitness.EvaluatorFactory;
import algorithm.fitness.MemberFitness;

import java.util.List;

public class PopulationEvolver {

    private final PopulationMutator populationMutator;
    private final PopulationCrosser populationCrosser;
    private FittestMemberFinder fittestMemberFinder;

    public PopulationEvolver(PopulationMutator populationMutator, PopulationCrosser populationCrosser) {
        this.populationMutator = populationMutator;
        this.populationCrosser = populationCrosser;
    }

    public Population evolve(Population initialPopulation) {

        // TODO population should be small, contain the best of the previous generation?
        // TODO this should then seed a new population (possibly add to previous?)  via crossover
        // TODO perform mutation
        // TODO select best individuals are start again  - breeding technique


        MemberFitness memberFitness = new MemberFitness(new EvaluatorFactory());
        fittestMemberFinder = new FittestMemberFinder(memberFitness);


        return rec(initialPopulation, 0);
//
//        for (int index = 0; index < 100; index ++) {
//            Population population = populationCrosser.crossover(initialPopulation);
//            Population mutatedPopulation = populationMutator.mutate(population);
//
//            List<Member> members = fittestMemberFinder.find(mutatedPopulation, 4);
//            Population fittestPopulationMembers = new Population(members);
//
//        }
    }

    private Population rec(Population seed, int count) {
        Population population = populationCrosser.crossover(seed);
        Population mutatedPopulation = populationMutator.mutate(population);
        List<Member> members = fittestMemberFinder.find(mutatedPopulation, 4);
        Population fittestPopulationMembers = new Population(members);
        return count < 100 ? rec(fittestPopulationMembers, ++count) : fittestPopulationMembers;
    }

}
