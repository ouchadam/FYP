package algorithm.population;

import algorithm.gene.Chromosome;
import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ChromosomesShould extends TestWithMocks {

    @Test
    public void concat_genes_strings() {
        Chromosome chromosome = MemberHelper.createChromosome(1, 2, 3);
        Member member = MemberHelper.createMember(chromosome);

        assertThat(member.toString()).isEqualTo("Chromosomes{a=123, b=null, c=null, d=null}");
    }

    @Test
    public void equal_based_on_chromosome() {
        Chromosomes member_1 = MemberHelper.createChromosomes(createChromosome(1,1,1,1), createChromosome(1, 1, 1, 1));
        Chromosomes member_2 = MemberHelper.createChromosomes(createChromosome(1,1,1,1), createChromosome(1,1,1,1));

        assertThat(member_1).isEqualTo(member_2);
        assertThat(member_1.hashCode()).isEqualTo(member_2.hashCode());
    }

    @Test
    public void not_be_equal_when_chromosome_amount_is_different() {
        Chromosomes member_1 = MemberHelper.createChromosomes(createChromosome(1,1,1,1), createChromosome(1,1,1,1), createChromosome(1,1,1,1));
        Chromosomes member_2 = MemberHelper.createChromosomes(createChromosome(1,1,1,1), createChromosome(1,1,1,1));

        assertThat(member_1).isNotEqualTo(member_2);
        assertThat(member_1.hashCode()).isNotEqualTo(member_2.hashCode());
    }

    private Chromosome createChromosome(int... notes) {
        return MemberHelper.createChromosome(notes);
    }

}
