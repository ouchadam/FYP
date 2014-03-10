package com.ouchadam.fyp.algorithm;

import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;

public class GeneticAlgorithmCreatorTest extends TestWithMocks {

    @Mock GenerationCallback generationCallback;
    @Mock AlgorithmParams algorithmParams;
    @Mock GenerationHalter generationHalter;

    @Test
    public void create_valid_genetic_algorithm() {
        GeneticAlgorithmCreator geneticAlgorithmCreator = GeneticAlgorithmCreator.newInstance();

        GeneticAlgorithm geneticAlgorithm = geneticAlgorithmCreator.create(generationCallback, algorithmParams, generationHalter);

        assertThat(geneticAlgorithm).isNotNull();
    }
}
