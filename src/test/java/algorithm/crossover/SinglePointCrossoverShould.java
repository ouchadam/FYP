package algorithm.crossover;

import algorithm.Member;
import gene.Feature;
import gene.Gene;
import gene.Mutator;
import gene.feature.Note;
import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class SinglePointCrossoverShould extends TestWithMocks {

    /*

    3 gene single crossover combinations

    Original parents :
    1 | 2 | 3  parent x
    4 | 5 | 6  parent y

    0 : No crossover point - defaults to Parent Y

         1  |  2  |  3
        [4] | [5] | [6]

    1 :

        [1] |  2  |  3
         4  | [5] | [6]

    2 :

        [1] | [2] |  3
         4  |  5  | [6]

    3 :

        [1] | [2] | [3]
         4  |  5  |  6


     */

    private Member parentY;
    private Member parentX;

    @Override
    protected void before() {
        parentX = MemberHelper.createMember(1, 2, 3);
        parentY = MemberHelper.createMember(4, 5 , 6);
    }

    @Test
    public void merge_members_via_single_point_position_0() {
        Crossover singlePointCrossover = new SinglePointCrossover(0);

        Member offspring = singlePointCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(MemberHelper.createMember(4, 5, 6));
    }

    @Test
    public void merge_members_via_single_point_position_1() {
        Crossover singlePointCrossover = new SinglePointCrossover(1);

        Member offspring = singlePointCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(MemberHelper.createMember(1, 5, 6));
    }

    @Test
    public void merge_members_via_single_point_position_2() {
        Crossover singlePointCrossover = new SinglePointCrossover(2);

        Member offspring = singlePointCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(MemberHelper.createMember(1, 2, 6));
    }

    @Test
    public void merge_members_via_single_point_position_3() {
        Crossover singlePointCrossover = new SinglePointCrossover(3);

        Member offspring = singlePointCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(MemberHelper.createMember(1, 2, 3));
    }

}
