package com.ouchadam.fyp.algorithm.population;

import com.ouchadam.fyp.algorithm.Member;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Random;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MemberCreatorTest extends TestWithMocks {

    @Mock Random random;
    @Mock Member.Controller controller;
    private MemberCreator memberCreator;

    @Override
    protected void before() {
        memberCreator = new MemberCreator(controller, random);
    }

    @Test
    public void pick_a_random_value_for_each_child_note_and_type() throws Exception {
        memberCreator.createRandomMember();

        verify(random, times(2 * Member.CHILD_COUNT)).nextInt(anyInt());
    }
}
