package algorithm.population;

import java.util.Collections;
import java.util.List;

public class Population {

    private List<Member> members;

    public Population(List<Member> members) {
        this.members = Collections.unmodifiableList(members);
    }

    public List<Member> getMembers() {
        return members;
    }

    public int size() {
        return members.size();
    }
}
