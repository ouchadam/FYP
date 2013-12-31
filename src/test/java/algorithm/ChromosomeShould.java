package algorithm;

import algorithm.gene.Chromosome;
import org.junit.Test;

import helper.MemberHelper;
import helper.TestWithMocks;

import static org.fest.assertions.api.Assertions.assertThat;

public class ChromosomeShould extends TestWithMocks {

    @Test
    public void concat_genes_strings() {
        Chromosome chromosome = MemberHelper.createChromosome(1, 2, 3);

        assertThat(chromosome.toString()).isEqualTo("123");
    }

    @Test
    public void equal_based_on_genes() {
        Chromosome chromosome_1 = MemberHelper.createChromosome(1);
        Chromosome chromosome_2 = MemberHelper.createChromosome(1);

        assertThat(chromosome_1).isEqualTo(chromosome_2);
    }

}
