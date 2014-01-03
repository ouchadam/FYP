package algorithm.population;


import algorithm.gene.Chromosome;
import algorithm.gene.Gene;
import algorithm.gene.GeneManager;
import algorithm.gene.feature.*;
import algorithm.gene.mutator.*;

import java.util.Random;

public class MemberFactory {

    private final FeatureFactory featureFactory;

    public MemberFactory(FeatureFactory featureFactory) {
        this.featureFactory = featureFactory;
    }

    public Member createRandomParentMember() {
        return new Member(ChromosomeManager.newInstance(createRandomChromosome(), createRandomChromosome(), createRandomChromosome(), createRandomChromosome()));
    }

    private Chromosome createRandomChromosome() {
        return new Chromosome(GeneManager.newInstance(createRandomNoteGene(), createRandomOctaveGene(), createRandomLengthGene(), createRandomRestGene()));
    }

    private Gene<Note> createRandomNoteGene() {
        return new Gene<Note>(featureFactory.createNote(10), new NoteMutator(new RandomNumberPicker(new Random()), featureFactory));
    }

    private Gene<Octave> createRandomOctaveGene() {
        return new Gene<Octave>(featureFactory.createOctave(10), new OctaveMutator());
    }

    private Gene<Rest> createRandomRestGene() {
        return new Gene<Rest>(featureFactory.createRest(Rest.Value.HOLD), new RestMutator());
    }

    private Gene<Length> createRandomLengthGene() {
        return new Gene<Length>(featureFactory.createLength(10), new LengthMutator());
    }

}
