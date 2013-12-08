package algorithm;

import gene.Feature;
import gene.Gene;

import java.util.List;

public class Member {

    private final List<Gene<Feature>> genes;

    public Member(List<Gene<Feature>> genes) {
        this.genes = genes;
    }

    public Gene<Feature> getGene(int position) {
        return genes.get(position);
    }

    public int count() {
        return genes.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (genes != null ? !genes.equals(member.genes) : member.genes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return genes != null ? genes.hashCode() : 0;
    }

    @Override public String toString() {
        StringBuilder genesString = new StringBuilder();
        for (Gene<Feature> gene : genes) {
            genesString.append(gene.toString());
        }
        return genesString.toString();
    }
}
