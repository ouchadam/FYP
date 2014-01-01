package algorithm.population;

import algorithm.gene.Chromosome;
import algorithm.gene.RandomListPicker;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.*;

public class ChromosomeManagerShould extends TestWithMocks {

    @Mock RandomListPicker<Chromosome> chromosomePicker;

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_chromosome_counts_dont_match() throws Exception {
        int chromosomeCount = 100;
        Chromosome chromosome_1 = mock(Chromosome.class);
        Chromosome chromosome_2 = mock(Chromosome.class);

        new ChromosomeManager(chromosomeCount, null, chromosome_1, chromosome_2);
    }

    @Test
    public void mutate_a_child_chromosome() throws Exception {
        Chromosome chromosome = mock(Chromosome.class);
        int chromosomeCount = 1;
        when(chromosomePicker.get(anyList())).thenReturn(chromosome);
        ChromosomeManager chromosomeManager = new ChromosomeManager(chromosomeCount, chromosomePicker, chromosome);

        chromosomeManager.mutate();

        verify(chromosome).mutate();
    }

    @Test
    public void choose_a_random_chromosome_via_the_random_list_picker_when_mutating() {
        RandomListPicker<Chromosome> chromosomePicker = spy(new RandomListPicker<Chromosome>());
        ChromosomeManager chromosomeManager = new ChromosomeManager(2, chromosomePicker, mock(Chromosome.class), mock(Chromosome.class));

        chromosomeManager.mutate();

        verify(chromosomePicker).get(anyList());
    }
}
