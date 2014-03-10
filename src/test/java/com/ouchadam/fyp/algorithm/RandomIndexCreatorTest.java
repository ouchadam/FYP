package com.ouchadam.fyp.algorithm;

import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Spy;

import java.util.List;
import java.util.Random;

import static org.fest.assertions.api.Assertions.assertThat;

public class RandomIndexCreatorTest extends TestWithMocks {

    @Spy Random random;
    private RandomIndexCreator randomIndexCreator;

    @Override
    protected void before() {
        randomIndexCreator = new RandomIndexCreator(random);
    }

    @Test
    public void creates_the_correct_amount_of_indexes() {
        int indexToCreateCount = 4;
        int maximumRandomValue = 10;

        List<Integer> indexes = randomIndexCreator.create(indexToCreateCount, maximumRandomValue);

        assertThat(indexes).hasSize(indexToCreateCount);
    }

    @Test
    public void abides_to_the_maximum_value() {
        int indexToCreateCount = 1;
        int maximumRandomValue = 10;

        List<Integer> indexes = randomIndexCreator.create(indexToCreateCount, maximumRandomValue);

        assertThat(indexes.get(0)).isLessThan(maximumRandomValue);
    }
}
