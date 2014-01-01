package algorithm.population;

import algorithm.gene.Chromosome;
import helper.TestWithMocks;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ChromosomeManagerShould extends TestWithMocks {

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_chromosome_counts_dont_match() throws Exception {
        int chromosomeCount = 100;
        Chromosome chromosome_1 = mock(Chromosome.class);
        Chromosome chromosome_2 = mock(Chromosome.class);

        new ChromosomeManager(chromosomeCount, null, chromosome_1, chromosome_2);
    }

}
