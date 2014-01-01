package algorithm.population;

import java.util.List;

public class PopulationMutator {

    public Population evolve(Population population) {
        List<Member> members = population.getMembers();
        for (Member member : members) {
            member.mutate();
        }
        return new Population(members);
    }

}
