package algorithm.population;

import algorithm.gene.BasicMutator;
import algorithm.gene.Chromosome;
import algorithm.gene.Gene;
import algorithm.gene.GeneManager;
import algorithm.gene.feature.Length;
import algorithm.gene.feature.Note;
import algorithm.gene.feature.Octave;
import algorithm.gene.feature.Rest;

public class MemberFactory {

    public Member createRandomParentMember() {
        return new Member(ChromosomeManager.newInstance(createRandomChromosome(), createRandomChromosome(), createRandomChromosome(), createRandomChromosome()));
    }

    private Chromosome createRandomChromosome() {
        return new Chromosome(GeneManager.newInstance(createRandomNoteGene(), createRandomOctaveGene(), createRandomLengthGene(), createRandomRestGene()));
    }

    private Gene<Note> createRandomNoteGene() {
        return new Gene<Note>(new Note(10), new BasicMutator<Note>());
    }

    private Gene<Octave> createRandomOctaveGene() {
        return new Gene<Octave>(new Octave(10), new BasicMutator<Octave>());
    }

    private Gene<Rest> createRandomRestGene() {
        return new Gene<Rest>(new Rest(Rest.Value.HOLD), new BasicMutator<Rest>());
    }

    private Gene<Length> createRandomLengthGene() {
        return new Gene<Length>(new Length(10), new BasicMutator<Length>());
    }

}
