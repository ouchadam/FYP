package algorithm;

import gene.Feature;
import gene.Gene;

import java.util.List;

public class Chromosome {

    private final List<Gene<Feature>> genes;

    public Chromosome(List<Gene<Feature>> genes) {
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
        Chromosome chromosome = (Chromosome) o;
        if (genes != null ? !genes.equals(chromosome.genes) : chromosome.genes != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return genes != null ? genes.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder genesString = new StringBuilder();
        for (Gene<Feature> gene : genes) {
            genesString.append(gene.toString());
        }
        return genesString.toString();
    }

}
