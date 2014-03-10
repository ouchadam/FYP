package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import com.ouchadam.fyp.algorithm.population.Population;
import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static helper.IndexHelper.createIndexes;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class PopulationMutatorTest extends TestWithMocks {

    @Mock Random random;
    @Mock IndexManager indexManager;
    @Mock Mutator<Member> memberMutator;

    private PopulationMutator populationMutator;

    @Override
    protected void before() {
        populationMutator = new PopulationMutator(indexManager, random, memberMutator);
    }

    @Test
    public void mutate_population_members_at_positions() throws Exception {
        int mutationCount = 3;
        Population population = createPopulation(mutationCount);

        when(random.nextInt()).thenReturn(mutationCount);
        when(memberMutator.mutate(any(Member.class))).thenReturn(MemberHelper.createRandom());
        when(indexManager.create(anyInt(), anyInt())).thenReturn(createIndexes(0, 1, 2));

        populationMutator.mutate(population);

        verify(memberMutator, times(mutationCount)).mutate(any(Member.class));
    }

    @Test
    public void not_mutate_the_population_if_the_minimum_size_is_not_met() throws Exception {
        int populationSize = PopulationMutator.MINIMUM_POPULATION_SIZE - 1;
        Population population = createPopulation(populationSize);

        populationMutator.mutate(population);

        verifyZeroInteractions(memberMutator);
    }

    private Population createPopulation(int size) {
        List<Member> memberList = new ArrayList<Member>();
        for (int index = 0; index < size; index++) {
            memberList.add(MemberHelper.createRandom());
        }
        return new Population(memberList);
    }
}
