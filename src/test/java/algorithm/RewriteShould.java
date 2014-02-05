package algorithm;

import algorithm.crossover.binary.Binary;
import algorithm.crossover.population.Population;
import algorithm.crossover.population.PopulationCreator;
import algorithm.crossover.population.PopulationCrossover;
import helper.TestWithMocks;
import org.junit.Test;

public class RewriteShould extends TestWithMocks {

    @Test
    public void testName() {
        Population population = new PopulationCreator(new PopulationCreator.MemberCreator()).createPopulation(2);
        Population crossOverPopulation = new PopulationCrossover().crossover(population);
        System.out.println("");
        crossOverPopulation.forEachMember(printMember);
    }

    private final ForEach<Member> printMember = new ForEach<Member>() {
        @Override
        public void on(Member what) {
            what.forEachNote(printNote);
        }
    };

    private final ForEach<Binary> printNote = new ForEach<Binary>() {
        @Override
        public void on(Binary what) {
            System.out.println(what.toDecimal());
        }
    };

}
