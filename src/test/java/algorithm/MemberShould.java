package algorithm;

import algorithm.fitness.ChromosomeFitness;
import algorithm.population.ChromosomeManager;
import algorithm.population.Member;
import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class MemberShould extends TestWithMocks {

    @Mock ChromosomeManager chromosomeManager;
    @Mock ChromosomeFitness chromosomeFitness;

    @Test
    public void use_chromosomes_to_generate_toString() {
        Member member = new Member(chromosomeManager, chromosomeFitness);

        member.toString();

        verify(chromosomeManager).asString();
    }

    @Test
    public void equal_based_on_genes() {
        Member member_1 = MemberHelper.createMember(MemberHelper.createChromosome(1));
        Member member_2 = MemberHelper.createMember(MemberHelper.createChromosome(1));

        assertThat(member_1).isEqualTo(member_2);
        assertThat(member_1.hashCode()).isEqualTo(member_2.hashCode());
    }

    @Test
    public void note_equal_when_genes_are_different() {
        Member member_1 = MemberHelper.createMember(MemberHelper.createChromosome(1));
        Member member_2 = MemberHelper.createMember(MemberHelper.createChromosome(2));

        assertThat(member_1).isNotEqualTo(member_2);
        assertThat(member_1.hashCode()).isNotEqualTo(member_2.hashCode());
    }

}
