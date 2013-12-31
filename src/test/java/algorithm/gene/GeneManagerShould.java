package algorithm.gene;

import helper.TestWithMocks;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class GeneManagerShould extends TestWithMocks {

    @Test (expected = IllegalArgumentException.class)
    public void throw_exception_when_gene_counts_dont_match() throws Exception {
        int geneCount = 100;
        Gene gene_1 = mock(Gene.class);
        Gene gene_2 = mock(Gene.class);

        new GeneManager(geneCount, gene_1, gene_2);
    }
}
