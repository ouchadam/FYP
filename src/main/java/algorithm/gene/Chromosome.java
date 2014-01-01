package algorithm.gene;

public class Chromosome {

    private final GeneManager genes;

    public Chromosome(GeneManager geneManager) {
        this.genes = geneManager;
    }

    public Gene<? extends Feature> getGene(int position) {
        return genes.getGene(position);
    }

    public int count() {
        return genes.getSize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chromosome that = (Chromosome) o;
        if (genes != null ? !genes.equals(that.genes) : that.genes != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return genes != null ? genes.hashCode() : 0;
    }

    @Override
    public String toString() {
        return genes.toString();
    }

    public void mutate() {
        genes.mutate();
    }
}
