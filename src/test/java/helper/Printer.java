package helper;

import com.ouchadam.fyp.algorithm.ForEach;
import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.population.Population;

public class Printer {

    public static void print(Population population) {
        population.forEachMember(printMember);
    }

    public static void print(Member member) {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < member.all().notes().size(); index++) {
            if (index == 0) {
                builder.append(member.all().notes().get(index).decimal());
            } else {
                builder.append(" : ").append(member.all().notes().get(index).decimal());
            }
        }
        System.out.println(builder.toString());
    }

    private final static ForEach<Member> printMember = new ForEach<Member>() {
        @Override
        public void on(Member what) {
            print(what);
        }
    };

}
