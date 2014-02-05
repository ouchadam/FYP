package algorithm.crossover.population;

import algorithm.ForEach;
import algorithm.Member;

import java.util.Collections;
import java.util.List;

public class Population {

    private final List<Member> members;

    public Population(List<Member> members) {
        this.members = Collections.unmodifiableList(members);
    }

    public int size() {
        return members.size();
    }

    public Member get(int index) {
        return members.get(index);
    }

    public void forEachMember(ForEach<Member> forEach) {
        for (Member member : members) {
            forEach.on(member);
        }
    }

    public Population prune(int amount) {
        return new Population(members.subList(0, amount));
    }
}
