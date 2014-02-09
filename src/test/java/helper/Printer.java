package helper;

import algorithm.ForEach;
import algorithm.Member;
import algorithm.Note;
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

    private final static ForEach<Note> printNote = new ForEach<Note>() {
        @Override
        public void on(Note what) {
            System.out.println(what.decimal());
        }
    };

}
