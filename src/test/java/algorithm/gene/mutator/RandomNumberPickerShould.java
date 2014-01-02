package algorithm.gene.mutator;

import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Random;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RandomNumberPickerShould extends TestWithMocks {

    @Mock Random random;
    RandomNumberPicker randomNumberPicker;

    @Override
    protected void before() {
        randomNumberPicker = new RandomNumberPicker(random);
    }

    @Test
    public void provide_negative_numbers() throws Exception {
        int range = 10;
        int offset = 0;
        int randomPositiveRange = 5;
        when(random.nextInt(anyInt())).thenReturn(randomPositiveRange);

        int randomOffset = randomNumberPicker.getNumberFromRange(range, offset);

        assertThat(randomOffset).isNegative();
    }

    @Test
    public void use_a_random_integer_when_mutating() throws Exception {
        RandomNumberPicker randomNumberPicker = new RandomNumberPicker(random);

        randomNumberPicker.getNumberFromRange(5, 0);

        verify(random).nextInt(anyInt());
    }

}
