package helper;

import com.ouchadam.fyp.algorithm.ForEach;
import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.crossover.population.Population;

public class Printer {

    public static void print(Population population) {
        population.forEachMember(printMember);
    }

    public static void print(Member member) {
        member.forEachNote(printNote);
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
