package com.ouchadam.fyp.algorithm.crossover.binary;

import helper.TestWithMocks;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class BinaryPadderShould extends TestWithMocks {

    /*

        100 in binary = 1100100
        1   in binary = 1

    */

    private static final Binary BINARY_100 = new Binary(Integer.toBinaryString(100));
    private static final Binary BINARY_1 = new Binary(Integer.toBinaryString(1));
    private BinaryPadder binaryPadder;

    @Override
    protected void before() {
        binaryPadder = new BinaryPadder();
    }

    @Test
    public void prove_binary_needs_padding() throws Exception {
        assertThat(BINARY_100.wordLength()).isNotEqualTo(BINARY_1.wordLength());
    }

    @Test
    public void match_the_size_of_the_largest_input() {
        BinaryPadder.MatchedLengthBinary matchedBinaries = binaryPadder.pad(BINARY_100, BINARY_1);

        assertThat(matchedBinaries.binaryX.wordLength()).isEqualTo(BINARY_100.wordLength());
        assertThat(matchedBinaries.binaryY.wordLength()).isEqualTo(BINARY_100.wordLength());
    }

    @Test
    public void not_affect_the_integer_value() {
        BinaryPadder.MatchedLengthBinary matchedBinaries = binaryPadder.pad(BINARY_1, BINARY_100);
        List<Integer> values = new ArrayList<Integer>(2);
        values.add(matchedBinaries.binaryX.toDecimal());
        values.add(matchedBinaries.binaryY.toDecimal());

        assertThat(values).contains(BINARY_1.toDecimal(), BINARY_100.toDecimal());
    }

    @Test
    public void not_pad_when_unneeded_Three() throws Exception {
        Binary binaryThree = new Binary("11");
        int wordLength = 2;

        Binary paddedThree = binaryPadder.pad(binaryThree, wordLength);

        assertThat(binaryThree.wordLength()).isEqualTo(paddedThree.wordLength());
    }


    @Test
    public void not_pad_when_unneeded_Two() throws Exception {
        Binary binaryTwo = new Binary("01");
        int wordLength = 2;

        Binary paddedThree = binaryPadder.pad(binaryTwo, wordLength);

        assertThat(binaryTwo.wordLength()).isEqualTo(paddedThree.wordLength());
    }

}
