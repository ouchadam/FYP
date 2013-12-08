import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class FooShould {

    Foo foo;

    @Before
    public void setUp() throws Exception {
        this.foo = new Foo();
    }

    @Test
    public void testName() throws Exception {
        assertThat(foo.test()).isEqualTo(0);
    }
}
