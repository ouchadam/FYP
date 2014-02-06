package helper;

import algorithm.ForEach;
import algorithm.Member;
import algorithm.crossover.binary.Binary;
import algorithm.crossover.population.Population;

public class Printer {

    public static void print(Population population) {
        population.forEachMember(printMember);
    }

    private final static ForEach<Member> printMember = new ForEach<Member>() {
        @Override
        public void on(Member what) {
            what.forEachNote(printNote);
        }
    };

    private final static ForEach<Binary> printNote = new ForEach<Binary>() {
        @Override
        public void on(Binary what) {
            System.out.println(what.toDecimal());
        }
    };

}
