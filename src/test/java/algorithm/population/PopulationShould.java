package algorithm.population;

import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class PopulationShould extends TestWithMocks {

    @Test
    public void constructed_members_should_not_be_changed() {
        List<Member> members = MemberHelper.createMockMembers(4);

        Population population = new Population(members);

        assertThat(population.getMembers()).isEqualTo(members);
    }


    @Test
    public void constructed_member_size_should_not_be_changed() {
        int memberCount = 4;
        List<Member> members = MemberHelper.createMockMembers(memberCount);

        Population population = new Population(members);

        assertThat(population.size()).isEqualTo(memberCount);
    }
}
