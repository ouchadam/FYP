package algorithm.gene;

import algorithm.fitness.FitnessValue;
import algorithm.gene.feature.Length;
import algorithm.gene.feature.Note;
import algorithm.gene.feature.Octave;
import algorithm.gene.feature.Rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GeneManager {

    private static final int DEFAULT_GENE_COUNT = 4;
    private final List<Gene<? extends Feature>> geneList;
    private RandomListPicker<Gene<? extends Feature>> genePicker;

    public static GeneManager from(List<Gene<? extends Feature>> genes) {
        RandomListPicker<Gene<? extends Feature>> genePicker = new RandomListPicker<Gene<? extends Feature>>();
        return new GeneManager(genes.size(), genes, genePicker);
    }

    @SuppressWarnings({"unchecked", "varargs"})
    public static GeneManager newInstance(Gene<Note> a, Gene<Octave> b, Gene<Length> c, Gene<Rest> d) {
        RandomListPicker<Gene<? extends Feature>> genePicker = new RandomListPicker<Gene<? extends Feature>>();
        return new GeneManager(genePicker, DEFAULT_GENE_COUNT, a, b, c, d);
    }

    @SafeVarargs
    GeneManager(RandomListPicker<Gene<? extends Feature>> genePicker, int geneCount, Gene<? extends Feature>... genes) {
        this(geneCount, Arrays.asList(genes), genePicker);
    }

    GeneManager(int geneCount, List<Gene<? extends Feature>> genes, RandomListPicker<Gene<? extends Feature>> genePicker) {
        validate(geneCount, genes);
        this.geneList = Collections.unmodifiableList(genes);
        this.genePicker = genePicker;
    }

    private void validate(int geneCount, List<Gene<? extends Feature>> genes) {
        if (genes.size() != geneCount) {
            throw new IllegalArgumentException("Gene lengths are not matched, expected : " + geneCount + " but got " + genes.size());
        }
    }

    public Gene<? extends Feature> getGene(int position) {
        return geneList.get(position);
    }

    public int getSize() {
        return geneList.size();
    }

    @Override
    public String toString() {
        StringBuilder genesString = new StringBuilder();
        for (Gene<? extends Feature> gene : geneList) {
            genesString.append(gene.toString());
        }
        return genesString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneManager that = (GeneManager) o;
        if (geneList != null ? !geneList.equals(that.geneList) : that.geneList != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return geneList != null ? geneList.hashCode() : 0;
    }

    public void mutate() {
        genePicker.get(geneList).mutate();
    }

    public GeneFitnessValue getFitness() {
        List<FitnessValue> geneFitnessValues = new ArrayList<FitnessValue>(getSize());
        for (Gene<? extends Feature> gene : geneList) {
            geneFitnessValues.add(gene.getFitness());
        }
        return new GeneFitnessValue(geneFitnessValues);
    }

}
