package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import com.ouchadam.fyp.algorithm.mutation.BinaryBuilder;
import com.ouchadam.fyp.algorithm.mutation.BinaryMutator;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BinaryMutatorTest extends TestWithMocks {

    static final int RANDOM_MUTATION_PROBABILITY = 0;

    @Mock Random random;
    @Mock IndexManager indexManager;

    private BinaryMutator binaryMutator;

    @Test
    public void invert_the_mutated_value() throws Exception {
        createMutator(100);
        initIndexManagerWith(createMutationPositions(0));

        Binary binary = new Binary("0");

        Binary mutatedBinary = binaryMutator.mutate(binary);

        assertThat(mutatedBinary.getValue()).isEqualTo("1");
    }

    @Test
    public void be_able_to_mutate_any_position() throws Exception {
        createMutator(100);
        Binary binary = new Binary("0001");

        initIndexManagerWith(createMutationPositions(3, 1));

        Binary mutatedBinary = binaryMutator.mutate(binary);

        assertThat(mutatedBinary.getValue()).isEqualTo("0100");
    }

    @Test
    public void use_a_random_mutation_count_when_probability_is_0() throws Exception {
        createMutator(RANDOM_MUTATION_PROBABILITY);
        initIndexManagerWith(createMutationPositions(0));

        Binary binary = new Binary("0");

        binaryMutator.mutate(binary);

        verify(random).nextInt(binary.wordLength());
    }

    private List<Integer> createMutationPositions(int... positions) {
        List<Integer> indexes = new ArrayList<Integer>();
        for (int position : positions) {
            indexes.add(position);
        }
        return indexes;
    }

    private void createMutator(int mutationProbability) {
        binaryMutator = new BinaryMutator(mutationProbability, indexManager, random, new BinaryBuilder());
    }

    private void initIndexManagerWith(List<Integer> indexes) {
        when(indexManager.create(anyInt(), anyInt())).thenReturn(indexes);
        when(indexManager.isIndex(anyInt(), anyList())).thenCallRealMethod();
    }
}
