package algorithm.crossover;

import algorithm.gene.Chromosome;
import algorithm.population.Member;
import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MemberCrossoverShould extends TestWithMocks {

    @Mock Crossover<Chromosome> crossover;
    MemberCrossover memberCrossover;

    @Override
    protected void before() {
        memberCrossover = new MemberCrossover(crossover);
    }

    @Test
    public void crossover_all_child_chromosome() throws Exception {
        Member member_1 = MemberHelper.createUnfitMember();
        Member member_2 = MemberHelper.createUnfitMember();
        int crossoverCount = member_1.count();

        memberCrossover.crossover(member_1, member_2);

        verify(crossover, times(crossoverCount)).crossover(any(Chromosome.class), any(Chromosome.class));
    }
}
