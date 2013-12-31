package algorithm.population;

public class Member {

    private final Chromosomes chromosomes;

    public Member(Chromosomes chromosomes) {
        this.chromosomes = chromosomes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        if (chromosomes != null ? !chromosomes.equals(member.chromosomes) : member.chromosomes != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return chromosomes != null ? chromosomes.hashCode() : 0;
    }

    @Override
    public String toString() {
        return chromosomes.asString();
    }

}
