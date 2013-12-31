package algorithm;

import algorithm.population.Member;
import gene.Chromosome;
import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class MemberShould extends TestWithMocks {

    @Test
    public void concat_genes_strings() {
        Chromosome chromosome = MemberHelper.createChromosome(1, 2, 3);
        Member member = MemberHelper.createMember(chromosome);

        assertThat(member.toString()).isEqualTo("123");
    }

    @Test
    public void equal_based_on_genes() {
        Member member_1 = MemberHelper.createMember(MemberHelper.createChromosome(1));
        Member member_2 = MemberHelper.createMember(MemberHelper.createChromosome(1));

        assertThat(member_1).isEqualTo(member_2);
    }

}
