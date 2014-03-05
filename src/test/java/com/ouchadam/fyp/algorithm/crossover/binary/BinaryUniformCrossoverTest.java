package com.ouchadam.fyp.algorithm.crossover.binary;

import com.ouchadam.fyp.algorithm.IndexManager;
import com.ouchadam.fyp.algorithm.RandomIndexCreator;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class BinaryUniformCrossoverTest extends TestWithMocks {

    private static final Binary BINARY_0 = new Binary("0000");
    private static final Binary BINARY_15 = new Binary("1111");

    @Mock RandomIndexCreator randomIndexCreator;

    private IndexManager indexManager;
    private BinaryUniformCrossover binaryUniformCrossover;

    @Override
    protected void before() {
        indexManager = new IndexManager(randomIndexCreator);
        binaryUniformCrossover = new BinaryUniformCrossover(new BinaryPadder(), indexManager, 0, new Random());
    }

    @Test
    public void crossover_indexes_0_and_3() throws Exception {
        setCrossoverIndexes(0, 3);

        Binary binary = binaryUniformCrossover.matchedBinaryCrossover(BINARY_0, BINARY_15);

        assertThat(binary.getValue()).isEqualTo("0110");
    }

    @Test
    public void crossover_indexes_1_and_2() throws Exception {
        setCrossoverIndexes(1, 2);

        Binary binary = binaryUniformCrossover.matchedBinaryCrossover(BINARY_0, BINARY_15);

        assertThat(binary.getValue()).isEqualTo("1001");
    }

    private void setCrossoverIndexes(int... indexes) {
        List<Integer> indexList = new ArrayList<Integer>(BINARY_0.wordLength());
        for (int index : indexes) {
            indexList.add(index);
        }
        when(indexManager.create(anyInt(), anyInt())).thenReturn(indexList);
    }
}
