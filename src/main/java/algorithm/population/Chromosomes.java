package algorithm.population;

import algorithm.gene.Chromosome;

import java.util.ArrayList;
import java.util.List;

public class Chromosomes {

    private static final int CHROMOSONE_COUNT = 4;

    private final Chromosome a;
    private final Chromosome b;
    private final Chromosome c;
    private final Chromosome d;

    public Chromosomes(Chromosome a, Chromosome b, Chromosome c, Chromosome d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public List<Chromosome> asList() {
        List<Chromosome> chromosomeList = new ArrayList<Chromosome>(CHROMOSONE_COUNT);
        chromosomeList.add(a);
        chromosomeList.add(b);
        chromosomeList.add(c);
        chromosomeList.add(d);
        return chromosomeList;
    }

    @Override
    public String toString() {
        return asString();
    }

    public String asString() {
        return "Chromosomes{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chromosomes that = (Chromosomes) o;

        if (a != null ? !a.equals(that.a) : that.a != null) return false;
        if (b != null ? !b.equals(that.b) : that.b != null) return false;
        if (c != null ? !c.equals(that.c) : that.c != null) return false;
        if (d != null ? !d.equals(that.d) : that.d != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        result = 31 * result + (c != null ? c.hashCode() : 0);
        result = 31 * result + (d != null ? d.hashCode() : 0);
        return result;
    }

}
