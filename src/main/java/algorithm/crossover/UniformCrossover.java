package algorithm.crossover;

import algorithm.gene.Chromosome;
import algorithm.gene.Feature;
import algorithm.gene.Gene;

import java.util.ArrayList;
import java.util.List;

class UniformCrossover implements Crossover<Chromosome> {

    private final RandomParentSelector<Chromosome> randomParentSelector;

    UniformCrossover(RandomParentSelector<Chromosome> randomParentSelector) {
        this.randomParentSelector = randomParentSelector;
    }

    @Override
    public Chromosome crossover(Chromosome parentX, Chromosome parentY) {
        validateParents(parentX, parentY);
        return new Chromosome(mergeRandomParentGenes(parentX, parentY));
    }

    private void validateParents(Chromosome parentX, Chromosome parentY) {
        if (parentX.count() != parentY.count()) {
            throw new RuntimeException("Parent gene counts don't match, what's up with that?");
        }
    }

    private List<Gene<Feature>> mergeRandomParentGenes(Chromosome parentX, Chromosome parentY) {
        List<Gene<Feature>> genes = new ArrayList<Gene<Feature>>();
        for (int index = 0; index < parentX.count(); index ++) {
            Chromosome randomParent = randomParentSelector.getParent(parentX, parentY);
            Gene<Feature> gene = randomParent.getGene(index);
            genes.add(gene);
        }
        return genes;
    }

}
