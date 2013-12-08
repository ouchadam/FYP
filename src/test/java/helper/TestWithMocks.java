package helper;

import org.junit.Before;

import static org.mockito.MockitoAnnotations.initMocks;

public class TestWithMocks {

    @Before
    final public void setUp() throws Exception {
        initMocks(this);
        before();
    }

    protected void before() {
    }
}
