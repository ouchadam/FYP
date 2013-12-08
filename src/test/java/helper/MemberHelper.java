package helper;

import algorithm.Member;
import gene.Feature;
import gene.Gene;
import gene.Mutator;
import gene.feature.Note;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class MemberHelper {

    public static Member createMember(int... notes) {
        List<Gene<Feature>> genes = new ArrayList<Gene<Feature>>(notes.length);
        for (int note : notes) {
            genes.add(new Gene<Feature>(new Note(note), mock(Mutator.class)));
        }
        return new Member(genes);
    }

}