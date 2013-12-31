package algorithm.population;

import algorithm.gene.*;
import algorithm.gene.feature.Length;
import algorithm.gene.feature.Note;
import algorithm.gene.feature.Octave;
import algorithm.gene.feature.Rest;

public class BasicPopulationCreator implements PopulationCreator {

    private static final int POPULATION_SIZE = 200;

    @Override
    public Population create() {

        Member parentX = createParentMember();
        Member parentY = createParentMember();

        // TODO create 198 members from these parents via single point crossover

        return null;
    }

    private Member createParentMember() {
        return new Member(ChromosomeManager.newInstance(createRandom(), createRandom(), createRandom(), createRandom()));
    }

    private Chromosome createRandom() {
        return new Chromosome(GeneManager.newInstance(createRandomNoteGene(), createRandomOctaveGene(), createRandomLengthGene(), createRandomRestGene()));
    }

    private Gene<Note> createRandomNoteGene() {
        return new Gene<Note>(new Note(10), new BasicMutator<Note>());
    }

    private Gene<Octave> createRandomOctaveGene() {
        return new Gene<Octave>(new Octave(10), new BasicMutator<Octave>());
    }

    private Gene<Rest> createRandomRestGene() {
        return new Gene<Rest>(new Rest(true), new BasicMutator<Rest>());
    }

    private Gene<Length> createRandomLengthGene() {
        return new Gene<Length>(new Length(10), new BasicMutator<Length>());
    }

}
