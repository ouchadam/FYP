package algorithm.crossover;

import gene.Chromosome;
import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class SinglePointChromosomeCrossoverShould extends TestWithMocks {

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

    private Chromosome parentY;
    private Chromosome parentX;

    @Override
    protected void before() {
        parentX = MemberHelper.createChromosome(1, 2, 3);
        parentY = MemberHelper.createChromosome(4, 5, 6);
    }

    @Test
    public void merge_members_via_single_point_position_0() {
        Crossover<Chromosome> singlePointCrossover = new SinglePointCrossover(0);

        Chromosome offspring = singlePointCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(MemberHelper.createChromosome(4, 5, 6));
    }

    @Test
    public void merge_members_via_single_point_position_1() {
        Crossover<Chromosome> singlePointCrossover = new SinglePointCrossover(1);

        Chromosome offspring = singlePointCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(MemberHelper.createChromosome(1, 5, 6));
    }

    @Test
    public void merge_members_via_single_point_position_2() {
        Crossover<Chromosome> singlePointCrossover = new SinglePointCrossover(2);

        Chromosome offspring = singlePointCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(MemberHelper.createChromosome(1, 2, 6));
    }

    @Test
    public void merge_members_via_single_point_position_3() {
        Crossover<Chromosome> singlePointCrossover = new SinglePointCrossover(3);

        Chromosome offspring = singlePointCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(MemberHelper.createChromosome(1, 2, 3));
    }

}
