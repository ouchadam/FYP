package helper;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;

import java.util.ArrayList;
import java.util.List;

public class MemberRuleTest extends TestWithMocks {

    protected Member createMember(int[] noteValues) {
        List<Note> notes = new ArrayList<Note>();
        for (int noteValue : noteValues) {
            notes.add(Note.newInstance(noteValue));
        }
        return new Member(notes, new Member.Controller());
    }

}
