package algorithm;

import helper.TestWithMocks;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class MelodyMakerShould extends TestWithMocks {

    @Test
    public void make_melodies() {
        MelodyMaker melodyMaker = new MelodyMaker();

        Melody melody = melodyMaker.make();

        assertThat(melody).isNotNull();
    }
}
