package algorithm.population;

import algorithm.gene.Chromosome;

import java.util.*;

public class ChromosomeManager {

    private static final int CHROMOSONE_COUNT = 4;
    private final List<Chromosome> chromosomeList;

    public static ChromosomeManager newInstance(Chromosome a, Chromosome b, Chromosome c, Chromosome d) {
        return new ChromosomeManager(a, b, c ,d);
    }

    ChromosomeManager(Chromosome... chromosome) {
        validate(chromosome);
        this.chromosomeList = Arrays.asList(chromosome);
    }

    private void validate(Chromosome[] chromosome) {
        if (chromosome.length != CHROMOSONE_COUNT) {
            throw new IllegalArgumentException("Chromosome lengths are not matched, expected : " + CHROMOSONE_COUNT + " but got " + chromosome.length);
        }
    }

    @Override
    public String toString() {
        return asString();
    }

    public String asString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Chromosomes{");
        int index = 0;

        for (ListIterator<Chromosome> iterator = chromosomeList.listIterator(); iterator.hasNext(); ) {
            Chromosome chromosome = iterator.next();
            stringBuilder.append(index++ + "=" + getStringOrNull(chromosome));
            if (iterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    private String getStringOrNull(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChromosomeManager that = (ChromosomeManager) o;

        if (chromosomeList != null ? !chromosomeList.equals(that.chromosomeList) : that.chromosomeList != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return chromosomeList != null ? chromosomeList.hashCode() : 0;
    }
}
