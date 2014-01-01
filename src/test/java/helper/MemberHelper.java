package helper;

import algorithm.gene.*;
import algorithm.population.ChromosomeManager;
import algorithm.population.Member;
import algorithm.gene.feature.Note;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class MemberHelper {

    public static Chromosome createChromosome(int... notes) {
        List<Gene<? extends Feature>> genes = new ArrayList<Gene<? extends Feature>>(notes.length);
        for (int note : notes) {
            genes.add(new Gene<Feature>(new Note(note), mock(Mutator.class)));
        }
        return new Chromosome(GeneManager.from(genes));
    }

    public static Member createMember(Chromosome... chromosomeArray) {
        return new Member(createChromosomes(chromosomeArray));
    }

    public static Member createMember() {
        return new Member(createChromosomes(createChromosome(1,2,3,4), createChromosome(1,2,3,4), createChromosome(1,2,3,4), createChromosome(1,2,3,4)));
    }

    public static ChromosomeManager createChromosomes(Chromosome... chromosomeArray) {
        return ChromosomeManager.newInstance(getItemOrNull(chromosomeArray, 0), getItemOrNull(chromosomeArray, 1), getItemOrNull(chromosomeArray, 2), getItemOrNull(chromosomeArray, 3));
    }

    private static <T> T getItemOrNull(T[] array, int index) {
        if (array.length <= index) {
            return null;
        } else {
            return array[index];
        }
    }

}
