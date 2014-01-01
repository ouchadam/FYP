package algorithm.population;

import algorithm.gene.Chromosome;
import algorithm.gene.Gene;
import algorithm.gene.GeneManager;
import algorithm.gene.feature.Length;
import algorithm.gene.feature.Note;
import algorithm.gene.feature.Octave;
import algorithm.gene.feature.Rest;
import algorithm.gene.mutator.LengthMutator;
import algorithm.gene.mutator.NoteMutator;
import algorithm.gene.mutator.OctaveMutator;
import algorithm.gene.mutator.RestMutator;

public class MemberFactory {

    public Member createRandomParentMember() {
        return new Member(ChromosomeManager.newInstance(createRandomChromosome(), createRandomChromosome(), createRandomChromosome(), createRandomChromosome()));
    }

    private Chromosome createRandomChromosome() {
        return new Chromosome(GeneManager.newInstance(createRandomNoteGene(), createRandomOctaveGene(), createRandomLengthGene(), createRandomRestGene()));
    }

    private Gene<Note> createRandomNoteGene() {
        return new Gene<Note>(new Note(10), new NoteMutator());
    }

    private Gene<Octave> createRandomOctaveGene() {
        return new Gene<Octave>(new Octave(10), new OctaveMutator());
    }

    private Gene<Rest> createRandomRestGene() {
        return new Gene<Rest>(new Rest(Rest.Value.HOLD), new RestMutator());
    }

    private Gene<Length> createRandomLengthGene() {
        return new Gene<Length>(new Length(10), new LengthMutator());
    }

}
