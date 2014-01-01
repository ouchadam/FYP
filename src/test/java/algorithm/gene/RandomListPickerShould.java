package algorithm.gene;

import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Random;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RandomListPickerShould extends TestWithMocks {

    @Mock Random random;
    @Mock List<Stub> mockList;
    RandomListPicker<Stub> randomListPicker;

    @Override
    protected void before() {
        randomListPicker = new RandomListPicker<Stub>(random);
    }

    @Test
    public void pick_a_random_number_based_off_of_the_max_list_size() throws Exception {
        int listSize = 10;
        when(mockList.size()).thenReturn(listSize);
        when(mockList.get(anyInt())).thenReturn(new Stub());

        randomListPicker.get(mockList);

        verify(random).nextInt(listSize);
    }

    private static class Stub {}
}
