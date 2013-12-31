package algorithm.crossover;

import gene.Chromosome;
import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class UniformChromosomeCrossoverShould extends TestWithMocks {

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

    @Mock RandomParentSelector<Chromosome> randomParentSelector;

    Crossover<Chromosome> uniformCrossover;

    Chromosome parentX;
    Chromosome parentY;
    int randomCounter;

    @Override
    protected void before() {
        randomCounter = 0;
        uniformCrossover = new UniformCrossover(randomParentSelector);
        parentX = MemberHelper.createChromosome(1, 2);
        parentY = MemberHelper.createChromosome(3, 4);
    }

    @Test
    public void merge_members_via_uniform_crossover_combination_0() {
        Crossover<Chromosome> uniformCrossover = new UniformCrossover(randomParentSelector);
        fixRandomOrder(parentY, parentY);

        Chromosome offspring = uniformCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(MemberHelper.createChromosome(3, 4));
    }

    @Test
    public void merge_members_via_uniform_crossover_combination_1() {
        Crossover<Chromosome> uniformCrossover = new UniformCrossover(randomParentSelector);
        fixRandomOrder(parentX, parentY);

        Chromosome offspring = uniformCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(MemberHelper.createChromosome(1, 4));
    }

    @Test
    public void merge_members_via_uniform_crossover_combination_2() {
        Crossover<Chromosome> uniformCrossover = new UniformCrossover(randomParentSelector);
        fixRandomOrder(parentX, parentX);

        Chromosome offspring = uniformCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(MemberHelper.createChromosome(1, 2));
    }

    @Test
    public void merge_members_via_uniform_crossover_combination_3() {
        Crossover<Chromosome> uniformCrossover = new UniformCrossover(randomParentSelector);
        fixRandomOrder(parentY, parentX);

        Chromosome offspring = uniformCrossover.crossover(parentX, parentY);

        assertThat(offspring).isEqualTo(MemberHelper.createChromosome(3, 2));
    }

    private void fixRandomOrder(final Chromosome... chromosomes) {
        when(randomParentSelector.getParent(any(Chromosome.class), any(Chromosome.class))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                try {
                    return chromosomes[randomCounter];
                } finally {
                    randomCounter++;
                }
            }
        });
    }

}
