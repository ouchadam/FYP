package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.Key;

import com.ouchadam.fyp.analysis.ScaleCreator;
import org.junit.Test;

import helper.TestWithMocks;

import static org.fest.assertions.api.Assertions.assertThat;

public class ScaleCreatorTest extends TestWithMocks {

    private ScaleCreator scaleCreator;

    @Override
    protected void before() {
        scaleCreator = new ScaleCreator();
    }

    @Test
    public void create_the_correct_intervals_for_c_major_scale() throws Exception {
        int[] cmajor = new int[] {0, 2 , 4, 5, 7, 9 , 11};

        int[] scaleIntervals = scaleCreator.create(Key.C, ScaleCreator.Type.MAJOR);

        assertThat(scaleIntervals).isEqualTo(cmajor);
    }
}
