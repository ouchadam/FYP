package helper;

import algorithm.gene.Chromosome;
import algorithm.population.Chromosomes;
import algorithm.population.Member;
import algorithm.gene.Feature;
import algorithm.gene.Gene;
import algorithm.gene.Mutator;
import algorithm.gene.feature.Note;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class MemberHelper {

    public static Chromosome createChromosome(int... notes) {
        List<Gene<Feature>> genes = new ArrayList<Gene<Feature>>(notes.length);
        for (int note : notes) {
            genes.add(new Gene<Feature>(new Note(note), mock(Mutator.class)));
        }
        return new Chromosome(genes);
    }

    public static Member createMember(Chromosome... chromosomeArray) {
        return new Member(createChromosomes(chromosomeArray));
    }

    public static Chromosomes createChromosomes(Chromosome... chromosomeArray) {
        return new Chromosomes(getItemOrNull(chromosomeArray, 0), getItemOrNull(chromosomeArray, 1), getItemOrNull(chromosomeArray, 2), getItemOrNull(chromosomeArray, 3));
    }

    private static <T> T getItemOrNull(T[] array, int index) {
        if (array.length <= index) {
            return null;
        } else {
            return array[index];
        }
    }

}
