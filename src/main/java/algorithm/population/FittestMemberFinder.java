package algorithm.population;

import algorithm.fitness.MemberFitness;

import java.util.*;

public class FittestMemberFinder {

    private final MemberFitness memberFitness;

    public FittestMemberFinder(MemberFitness memberFitness) {
        this.memberFitness = memberFitness;
    }

    public List<Member> find(Population population, int count) {
        if (population.size() < count) {
            throw new RuntimeException("asked to find : " + count + " members but the population has : " + population.size());
        }
        List<FitnessIndex> fitnessValues = new ArrayList<FitnessIndex>(population.size());

        for (int index = 0; index < population.size(); index++) {
            int fitnessValue = memberFitness.evaluate(population.getMembers().get(index)).value();
            fitnessValues.add(new FitnessIndex(index, fitnessValue));
        }
        Collections.sort(fitnessValues);
        List<Member> fittestMembers = new ArrayList<Member>(count);

        for (int index = 0; index < count; index++) {
            FitnessIndex fitnessIndex = fitnessValues.get(index);
            Member fitMember = population.getMembers().get(fitnessIndex.populationIndex);
            fittestMembers.add(fitMember);
        }
        validateFittest(fittestMembers, count);
        return fittestMembers;
    }

    private void validateFittest(List<Member> fittestMembers, int count) {
        if (fittestMembers.size() != count) {
            throw new RuntimeException("asked for " + count + " members but got : " + fittestMembers.size());
        }
    }

    private static class FitnessIndex implements Comparable<FitnessIndex> {

        final Integer populationIndex;
        final Integer fitnessValue;

        private FitnessIndex(int populationIndex, int fitnessValue) {
            this.populationIndex = populationIndex;
            this.fitnessValue = fitnessValue;
        }

        @Override
        public int compareTo(FitnessIndex o) {
            return fitnessValue.compareTo(o.fitnessValue);
        }

    }

}
