package algorithm.population;

import algorithm.gene.Chromosome;

public class Member {

    private final ChromosomeManager chromosomeManager;

    public Member(ChromosomeManager chromosomeManager) {
        this.chromosomeManager = chromosomeManager;
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
}
