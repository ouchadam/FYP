package helper;

import com.ouchadam.fyp.algorithm.domain.Member;

public class Printer {

    public static void printMember(Member member) {
        System.out.println(getMemberAsString(member));
    }

    public static String getMemberAsString(Member member) {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < member.all().noteValues().size(); index++) {
            if (index == 0) {
                builder.append(member.all().noteValues().get(index).decimal());
            } else {
                builder.append(" : ").append(member.all().noteValues().get(index).decimal());
            }
        }
        return builder.toString();
    }

}
