package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import com.ouchadam.fyp.algorithm.crossover.binary.Bit;
import helper.TestWithMocks;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class BinaryBuilderTest extends TestWithMocks {

    private BinaryBuilder binaryBuilder;

    @Override
    protected void before() {
        binaryBuilder = new BinaryBuilder();
    }

    @Test
    public void build_binary_strings_using_the_input_order() throws Exception {
        binaryBuilder.start(2);

        binaryBuilder.addBit(Bit.on());
        binaryBuilder.addBit(Bit.off());

        Binary result = binaryBuilder.build();

        assertThatBinaryIs(result, "10", 2);
    }

    private static void assertThatBinaryIs(Binary build, String binaryString, int decimal) {
        assertThat(build.getValue()).isEqualTo(binaryString);
        assertThat(build.toDecimal()).isEqualTo(decimal);
    }
}
