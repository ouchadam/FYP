package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MemberMutatorTest extends TestWithMocks {

    private MemberMutator memberMutator;

    @Mock IndexManager indexManager;
    @Mock Random random;
    @Mock Mutator<Binary> binaryMutator;
    @Mock Member.Controller controller;

    @Override
    protected void before() {
        memberMutator = new MemberMutator(indexManager, random, binaryMutator, controller);
    }

    @Test
    public void mutate_member_values_at_positions() throws Exception {
        Member member = MemberHelper.createRandom();

        when(indexManager.create(anyInt(), anyInt())).thenReturn(createIndexes(0, 2));

        when(random.nextFloat()).thenReturn(0.4f);

        memberMutator.mutate(member);

        verify(binaryMutator, times(2)).mutate(any(Binary.class));
    }

    @Test
    public void mutate_member_types_at_positions() throws Exception {
        Member member = MemberHelper.createRandom();

        when(binaryMutator.mutate(any(Binary.class))).thenReturn(new Binary("01"));
        when(indexManager.create(anyInt(), anyInt())).thenReturn(createIndexes(0, 2));

        when(random.nextFloat()).thenReturn(0.9f);

        memberMutator.mutate(member);

        verify(binaryMutator, times(2)).mutate(any(Binary.class));
    }

}
