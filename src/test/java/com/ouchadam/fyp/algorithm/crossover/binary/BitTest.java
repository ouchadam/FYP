package com.ouchadam.fyp.algorithm.crossover.binary;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class BitTest {

    private static final int ON_BIT = 1;
    private static final int OFF_BIT = 0;

    @Test
    public void correctly_invert_1_to_0() throws Exception {
        Bit bit = new Bit(ON_BIT);

        Bit invertedBit = bit.invert();

        assertThat(invertedBit.value()).isEqualTo(OFF_BIT);
    }

    @Test
    public void correctly_invert_0_to_1() throws Exception {
        Bit bit = new Bit(OFF_BIT);

        Bit invertedBit = bit.invert();

        assertThat(invertedBit.value()).isEqualTo(ON_BIT);
    }
}
