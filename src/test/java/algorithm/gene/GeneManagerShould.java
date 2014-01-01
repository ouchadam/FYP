package algorithm.gene;

import algorithm.population.ChromosomeManager;
import helper.TestWithMocks;
import org.junit.Test;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.*;

public class GeneManagerShould extends TestWithMocks {

    @Test (expected = IllegalArgumentException.class)
    public void throw_exception_when_gene_counts_dont_match() throws Exception {
        int geneCount = 100;
        Gene gene_1 = mock(Gene.class);
        Gene gene_2 = mock(Gene.class);

        new GeneManager(null, geneCount, gene_1, gene_2);
    }

    @Test
    public void mutate_a_child_gene() throws Exception {
        Gene gene = mock(Gene.class);
        int geneCount = 1;
        RandomListPicker<Gene<? extends Feature>> genePicker = new RandomListPicker<Gene<? extends Feature>>();
        GeneManager geneManager = new GeneManager(genePicker, geneCount, gene);

        geneManager.mutate();

        verify(gene).mutate();
    }

    @Test
    public void choose_a_random_gene_via_the_random_list_picker_when_mutating() {
        RandomListPicker<Gene<? extends Feature>> genePicker = spy(new RandomListPicker<Gene<? extends Feature>>());
        GeneManager geneManager = new GeneManager(genePicker, 2, mock(Gene.class), mock(Gene.class));

        geneManager.mutate();

        verify(genePicker).get(anyList());
    }
}
