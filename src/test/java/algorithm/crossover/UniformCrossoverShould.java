package algorithm.crossover;

import algorithm.Member;
import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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

    UniformCrossover uniformCrossover;

    Member parentX;
    Member parentY;
    int randomCounter;

    @Override
    protected void before() {
        randomCounter = 0;
        uniformCrossover = new UniformCrossover(randomParentSelector);
        parentX = MemberHelper.createMember(1, 2);
        parentY = MemberHelper.createMember(3, 4);
    }

    @Test
    public void merge_members_via_uniform_crossover_combination_0() {
        Crossover uniformCrossover = new UniformCrossover(randomParentSelector);
        fixRandomOrder(parentY, parentY);

        Member offspring = uniformCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(MemberHelper.createMember(3, 4));
    }

    @Test
    public void merge_members_via_uniform_crossover_combination_1() {
        Crossover uniformCrossover = new UniformCrossover(randomParentSelector);
        fixRandomOrder(parentX, parentY);

        Member offspring = uniformCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(MemberHelper.createMember(1, 4));
    }

    @Test
    public void merge_members_via_uniform_crossover_combination_2() {
        Crossover uniformCrossover = new UniformCrossover(randomParentSelector);
        fixRandomOrder(parentX, parentX);

        Member offspring = uniformCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(MemberHelper.createMember(1, 2));
    }

    @Test
    public void merge_members_via_uniform_crossover_combination_3() {
        Crossover uniformCrossover = new UniformCrossover(randomParentSelector);
        fixRandomOrder(parentY, parentX);

        Member offspring = uniformCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(MemberHelper.createMember(3, 2));
    }

    private void fixRandomOrder(final Member... members) {
        when(randomParentSelector.getParent(any(Member.class), any(Member.class))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                try {
                    return members[randomCounter];
                } finally {
                    randomCounter++;
                }
            }
        });
    }

}
