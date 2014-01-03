package algorithm.population;

import algorithm.fitness.ChromosomeFitness;
import algorithm.fitness.FitnessValue;
import algorithm.gene.Chromosome;

public class Member {

    private final ChromosomeManager chromosomeManager;
    private final ChromosomeFitness chromosomeFitness;

    public Member(ChromosomeManager chromosomeManager, ChromosomeFitness chromosomeFitness) {
        this.chromosomeManager = chromosomeManager;
        this.chromosomeFitness = chromosomeFitness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        if (chromosomeManager != null ? !chromosomeManager.equals(member.chromosomeManager) : member.chromosomeManager != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return chromosomeManager != null ? chromosomeManager.hashCode() : 0;
    }

    @Override
    public String toString() {
        return chromosomeManager.asString();
    }

    public int count() {
        return chromosomeManager.size();
    }

    public Chromosome get(int index) {
        return chromosomeManager.get(index);
    }

    public void mutate() {
        chromosomeManager.mutate();
    }

    public FitnessValue getFitness() {
        return chromosomeFitness.evaluate(chromosomeManager);
    }

}
