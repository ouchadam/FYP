package com.ouchadam.fyp.algorithm.population;

import com.ouchadam.fyp.algorithm.domain.Member;

import java.util.*;

public class Population {

    private final List<Member> members;

    public static Population fromSubPopulation(Population... populations) {
        List<Member> newPopulation = new ArrayList<Member>();
        for (Population population : populations) {
            newPopulation.addAll(population.members);
        }
        return new Population(newPopulation);
    }

    public Population(List<Member> members) {
        this.members = Collections.unmodifiableList(members);
    }

    public int size() {
        return members.size();
    }

    public Member get(int index) {
        return members.get(index);
    }

    public List<Member> all() {
        return new ArrayList<Member>(members);
    }

    public Population getSubPopulation(int from, int to) {
        return new Population(members.subList(from, to));
    }

    public int frequency(Member member) {
        return Collections.frequency(members, member);
    }

    public int indexOf(Member what) {
        return members.indexOf(what);
    }

    public Population removeDuplicates() {
        Set<Member> set = new HashSet<Member>();
        set.addAll(members);
        return new Population(new ArrayList<Member>(set));
    }

    public Population shuffle() {
        List<Member> shuffledMembers = new ArrayList<Member>(members);
        Collections.shuffle(shuffledMembers);
        return new Population(shuffledMembers);
    }
}
