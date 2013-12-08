import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class EntryShould extends TestWithMocks {

    @Mock EntryDelegate entryDelegate;
    Entry entry;

    @Override
    protected void before() {
        entry = new Entry(entryDelegate);
    }

    @Test
    public void should_delegate_main_call() throws Exception {
        String[] args = {"args"};

        entry.instanceMain(args);

        verify(entryDelegate).delegate(args);
    }
}
