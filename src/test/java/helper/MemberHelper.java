package helper;

import algorithm.fitness.MemberFitness;
import algorithm.gene.*;
import algorithm.gene.feature.FeatureFactory;
import algorithm.population.ChromosomeManager;
import algorithm.population.Member;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class MemberHelper {

    public static Chromosome createChromosome(int... notes) {
        FeatureFactory featureFactory = FeatureFactory.newInstance();

        List<Gene<? extends Feature<?>, ?>> genes = new ArrayList<Gene<? extends Feature<?>, ?>>(notes.length);
        for (int note : notes) {
            genes.add(new Gene<Feature<Integer>, Integer>(featureFactory.createNote(note), mock(Mutator.class)));
        }
        return new Chromosome(GeneManager.from(genes));
    }

    public static Member createMember(Chromosome... chromosomeArray) {
        return new Member(createChromosomes(chromosomeArray));
    }

    public static Member createUnfitMember() {
        return new Member(createChromosomes(createChromosome(1, 2, 3, 4), createChromosome(1, 2, 3, 4), createChromosome(1, 2, 3, 4), createChromosome(1, 2, 3, 4)));
    }


    public static Member createFitMember() {
        return new Member(createChromosomes(createChromosome(10, 10, 10, 10), createChromosome(10, 10, 10, 10), createChromosome(10, 10, 10, 10), createChromosome(10, 10, 10, 10)));
    }

    public static ChromosomeManager createChromosomes(Chromosome... chromosomeArray) {
        return ChromosomeManager.newInstance(getItemOrNull(chromosomeArray, 0), getItemOrNull(chromosomeArray, 1), getItemOrNull(chromosomeArray, 2), getItemOrNull(chromosomeArray, 3));
    }

    public static List<Member> createMockMembers(int memberCount) {
        List<Member> members = new ArrayList<Member>();
        for (int index = 0; index < memberCount; index++) {
            members.add(mock(Member.class));
        }
        return members;
    }

    private static <T> T getItemOrNull(T[] array, int index) {
        if (array.length <= index) {
            return null;
        } else {
            return array[index];
        }
    }

}
