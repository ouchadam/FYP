package algorithm;

import gene.Feature;
import gene.Gene;

import java.util.ArrayList;
import java.util.List;

class SinglePointCrossover implements MemberBreeder.Crossover {

    private final int position;

    SinglePointCrossover(int position) {
        this.position = position;
    }

    @Override
    public Member crossover(Member parentX, Member parentY) {
        List<Gene<Feature>> parentXGenes = getGenesFromParent(parentX, 0, position);
        List<Gene<Feature>> parentYGenes = getGenesFromParent(parentY, position, parentY.count());

        return new Member(mergeGenes(parentXGenes, parentYGenes));
    }

    private List<Gene<Feature>> getGenesFromParent(Member parent, int from, int to) {
        List<Gene<Feature>> genes = new ArrayList<Gene<Feature>>();
        for (int index = from; index < to; index ++) {
            Gene<Feature> gene = parent.getGene(index);
            genes.add(gene);
        }
        return genes;
    }

    private List<Gene<Feature>> mergeGenes(List<Gene<Feature>> parentXGenes, List<Gene<Feature>> parentYGenes) {
        List<Gene<Feature>> genes = new ArrayList<Gene<Feature>>();
        genes.addAll(parentXGenes);
        genes.addAll(parentYGenes);
        return genes;
    }
}
