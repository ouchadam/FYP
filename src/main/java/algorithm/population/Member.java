package algorithm.population;

import java.util.List;

import gene.Chromosome;

public class Member {

    private final List<Chromosome> chromosomes;

    public Member(List<Chromosome> chromosomes) {
        this.chromosomes = chromosomes;
    }

    public Chromosome getChromosome(int position) {
        return chromosomes.get(position);
    }

    public int count() {
        return chromosomes.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        if (chromosomes != null ? !chromosomes.equals(member.chromosomes) : member.chromosomes != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return chromosomes != null ? chromosomes.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder chromosoneString = new StringBuilder();
        for (Chromosome chromosome : chromosomes) {
            chromosoneString.append(chromosome.toString());
        }
        return chromosoneString.toString();
    }
}
