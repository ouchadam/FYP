package helper;

import com.ouchadam.fyp.algorithm.domain.Member;
import com.ouchadam.fyp.algorithm.domain.NoteType;
import com.ouchadam.fyp.algorithm.domain.NoteValue;
import com.ouchadam.fyp.presentation.midi.NoteOnFilter;

import java.util.ArrayList;
import java.util.List;

public class MemberRuleTest extends TestWithMocks {

    protected Member createMember(int[] noteValues) {
        List<NoteValue> notes = new ArrayList<NoteValue>();
        List<NoteType> noteTypes = new ArrayList<NoteType>();
        for (int noteValue : noteValues) {
            notes.add(NoteValue.newInstance(noteValue));
            noteTypes.add(NoteType.NOTE);
        }
        return new Member(notes, noteTypes, new Member.Controller(new NoteOnFilter()));
    }

}
