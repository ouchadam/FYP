package algorithm.crossover;

import algorithm.Member;
import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class RandomParentSelectorShould extends TestWithMocks {

    @Mock RandomTrueFalseGenerator trueFalseGenerator;

    Member parentX;
    Member parentY;
    private RandomParentSelector randomParentSelector;

    @Override
    protected void before() {
        parentX = MemberHelper.createMember(1);
        parentY = MemberHelper.createMember(2);
        randomParentSelector = new RandomParentSelector(trueFalseGenerator);
    }

    @Test
    public void select_parentx_when_random_selection_is_false() {
        when(trueFalseGenerator.get()).thenReturn(false);

        Member parent = randomParentSelector.getParent(parentX, parentY);

        assertThat(parent).isEqualTo(parentX);
    }

    @Test
    public void select_parenty_when_random_selection_is_true() {
        when(trueFalseGenerator.get()).thenReturn(true);

        Member parent = randomParentSelector.getParent(parentX, parentY);

        assertThat(parent).isEqualTo(parentY);
    }

}
