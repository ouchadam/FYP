package algorithm.population;

import algorithm.gene.Chromosome;

import java.util.*;

public class ChromosomeManager {

    private static final int DEFAULT_CHROMOSONE_COUNT = 4;
    private final List<Chromosome> chromosomeList;

    public static ChromosomeManager from(List<Chromosome> chromosomeList) {
        return new ChromosomeManager(chromosomeList.size(), chromosomeList);
    }

    public static ChromosomeManager newInstance(Chromosome a, Chromosome b, Chromosome c, Chromosome d) {
        return new ChromosomeManager(DEFAULT_CHROMOSONE_COUNT, a, b, c ,d);
    }

    ChromosomeManager(int count, Chromosome... chromosome) {
        this(count, Arrays.asList(chromosome));
    }

    private ChromosomeManager(int count, List<Chromosome> chromosomeList) {
        validate(count, chromosomeList);
        this.chromosomeList = Collections.unmodifiableList(chromosomeList);
    }

    private void validate(int count, List<Chromosome> chromosome) {
        if (chromosome.size() != count) {
            throw new IllegalArgumentException("Chromosome lengths are not matched, expected : " + count + " but got " + chromosome.size());
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

    public int size() {
        return chromosomeList.size();
    }

    public Chromosome get(int index) {
        return chromosomeList.get(index);
    }
}