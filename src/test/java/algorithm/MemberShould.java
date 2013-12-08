package algorithm;

import org.junit.Test;

import helper.MemberHelper;
import helper.TestWithMocks;

import static org.fest.assertions.api.Assertions.assertThat;

public class MemberShould extends TestWithMocks {

    @Test
    public void concat_genes_strings() {
        Member member = MemberHelper.createMember(1, 2, 3);

        assertThat(member.toString()).isEqualTo("123");
    }

    @Test
    public void equal_based_on_genes() {
        Member member_1 = MemberHelper.createMember(1);
        Member member_2 = MemberHelper.createMember(1);

        assertThat(member_1).isEqualTo(member_2);
    }

}
