package algorithm.crossover;

import algorithm.gene.Chromosome;
import algorithm.gene.Feature;
import algorithm.gene.Gene;
import algorithm.gene.GeneManager;

import java.util.ArrayList;
import java.util.List;

public class SinglePointChromosomeCrossover implements Crossover<Chromosome> {

    private final int position;

    public SinglePointChromosomeCrossover(int position) {
        this.position = position;
    }

    @Override
    public Chromosome crossover(Chromosome parentX, Chromosome parentY) {
        List<Gene<? extends Feature<?>, ?>> parentXGenes = getGenesFromParent(parentX, 0, position);
        List<Gene<? extends Feature<?>, ?>> parentYGenes = getGenesFromParent(parentY, position, parentY.count());

        return new Chromosome(GeneManager.from(mergeGenes(parentXGenes, parentYGenes)));
    }

    private List<Gene<? extends Feature<?>, ?>> getGenesFromParent(Chromosome parent, int from, int to) {
        List<Gene<? extends Feature<?>, ?>> genes = new ArrayList<Gene<? extends Feature<?>, ?>>();
        for (int index = from; index < to; index ++) {
            Gene<? extends Feature<?>, ?> gene = parent.getGene(index);
            genes.add(gene);
        }
        return genes;
    }

    private List<Gene<? extends Feature<?>, ?>> mergeGenes(List<Gene<? extends Feature<?>, ?>> parentXGenes, List<Gene<? extends Feature<?>, ?>> parentYGenes) {
        List<Gene<? extends Feature<?>, ?>> genes = new ArrayList<Gene<? extends Feature<?>, ?>>();
        genes.addAll(parentXGenes);
        genes.addAll(parentYGenes);
        return genes;
    }
}
