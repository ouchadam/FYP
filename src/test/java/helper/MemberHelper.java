package helper;

import algorithm.Chromosome;
import algorithm.Member;
import gene.Feature;
import gene.Gene;
import gene.Mutator;
import gene.feature.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;

public class MemberHelper {

    public static Chromosome createChromosome(int... notes) {
        List<Gene<Feature>> genes = new ArrayList<Gene<Feature>>(notes.length);
        for (int note : notes) {
            genes.add(new Gene<Feature>(new Note(note), mock(Mutator.class)));
        }
        return new Chromosome(genes);
    }

    public static Member createMember(Chromosome... chromosomes) {
        return new Member(Arrays.asList(chromosomes));
    }

}
