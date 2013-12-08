package algorithm;

import gene.Feature;
import gene.Gene;
import gene.Mutator;
import gene.feature.Note;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class UniformCrossoverShould extends TestWithMocks {

    /*

    2 gene uniform crossover combinations, parent selection is random.

    Original parents :
    1 | 2  parent x
    3 | 4  parent y

    0 :

         1  |  2
        [3] | [4]

    1 :

        [1] |  2
         3  | [4]

    2 :

        [1] | [2]
         3  |  4

    3 :

        1 | [2]
       [3]|  4


     */

    @Mock RandomParentSelector randomParentSelector;
    @Mock Mutator mutator;

    UniformCrossover uniformCrossover;

    Member parentX;
    Member parentY;
    int randomCounter;

    @Override
    protected void before() {
        randomCounter = 0;
        uniformCrossover = new UniformCrossover(randomParentSelector);
        parentX = createMember(1, 2);
        parentY = createMember(3, 4);
    }

    @Test
    public void merge_members_via_uniform_crossover_combination_0() {
        MemberBreeder.Crossover uniformCrossover = new UniformCrossover(randomParentSelector);
        fixRandomOrder(parentY, parentY);

        Member offspring = uniformCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(createMember(3, 4));
    }

    @Test
    public void merge_members_via_uniform_crossover_combination_1() {
        MemberBreeder.Crossover uniformCrossover = new UniformCrossover(randomParentSelector);
        fixRandomOrder(parentX, parentY);

        Member offspring = uniformCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(createMember(1, 4));
    }


    @Test
    public void merge_members_via_uniform_crossover_combination_2() {
        MemberBreeder.Crossover uniformCrossover = new UniformCrossover(randomParentSelector);
        fixRandomOrder(parentX, parentX);

        Member offspring = uniformCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(createMember(1, 2));
    }


    @Test
    public void merge_members_via_uniform_crossover_combination_3() {
        MemberBreeder.Crossover uniformCrossover = new UniformCrossover(randomParentSelector);
        fixRandomOrder(parentY, parentX);

        Member offspring = uniformCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(createMember(3, 2));
    }


    private void fixRandomOrder(final Member... members) {
        when(randomParentSelector.getParent(any(Member.class), any(Member.class))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                try {
                return members[randomCounter];
                } finally {
                    randomCounter ++;
                }
            }
        });
    }

    private Member createMember(int... notes) {
        List<Gene<Feature>> genes = new ArrayList<Gene<Feature>>(notes.length);
        for (int note : notes) {
            genes.add(new Gene<Feature>(new Note(note), mutator));
        }
        return new Member(genes);
    }

}
