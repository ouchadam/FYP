package algorithm.crossover;

import gene.Chromosome;
import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class RandomParentSelectorShould extends TestWithMocks {

    @Mock RandomTrueFalseGenerator trueFalseGenerator;

    Chromosome parentX;
    Chromosome parentY;
    private RandomParentSelector<Chromosome> randomParentSelector;

    @Override
    protected void before() {
        parentX = MemberHelper.createChromosome(1);
        parentY = MemberHelper.createChromosome(2);
        randomParentSelector = new RandomParentSelector<Chromosome>(trueFalseGenerator);
    }

    @Test
    public void select_parentx_when_random_selection_is_false() {
        when(trueFalseGenerator.get()).thenReturn(false);

        Chromosome parent = randomParentSelector.getParent(parentX, parentY);

        assertThat(parent).isEqualTo(parentX);
    }

    @Test
    public void select_parenty_when_random_selection_is_true() {
        when(trueFalseGenerator.get()).thenReturn(true);

        Chromosome parent = randomParentSelector.getParent(parentX, parentY);

        assertThat(parent).isEqualTo(parentY);
    }

}
