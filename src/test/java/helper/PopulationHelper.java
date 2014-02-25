package helper;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.crossover.population.Population;

import java.util.ArrayList;
import java.util.List;

public class PopulationHelper {

    public static Population create() {
        List<Member> members = new ArrayList<Member>();
        members.add(MemberHelper.createRandom());
        members.add(MemberHelper.createRandom());
        members.add(MemberHelper.createRandom());
        members.add(MemberHelper.createRandom());
        members.add(MemberHelper.createRandom());
        return new Population(members);
    }
}
