package algorithm.crossover;

import algorithm.Member;
import gene.Feature;
import gene.Gene;

import java.util.ArrayList;
import java.util.List;

class UniformCrossover implements Crossover {

    private final RandomParentSelector randomParentSelector;

    UniformCrossover(RandomParentSelector randomParentSelector) {
        this.randomParentSelector = randomParentSelector;
    }

    @Override
    public Member crossover(Member parentX, Member parentY) {
        validateParents(parentX, parentY);
        return new Member(mergeRandomParentGenes(parentX, parentY));
    }

    private void validateParents(Member parentX, Member parentY) {
        if (parentX.count() != parentY.count()) {
            throw new RuntimeException("Parent gene counts don't match, what's up with that?");
        }
    }

    private List<Gene<Feature>> mergeRandomParentGenes(Member parentX, Member parentY) {
        List<Gene<Feature>> genes = new ArrayList<Gene<Feature>>();
        for (int index = 0; index < parentX.count(); index ++) {
            Member randomParent = randomParentSelector.getParent(parentX, parentY);
            Gene<Feature> gene = randomParent.getGene(index);
            genes.add(gene);
        }
        return genes;
    }

}
