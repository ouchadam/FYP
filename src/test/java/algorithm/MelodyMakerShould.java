package algorithm;

import helper.TestWithMocks;
import marshall.Marshaller;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MelodyMakerShould extends TestWithMocks {

    @Mock GeneticAlgorithm geneticAlgorithm;
    @Mock Marshaller<Member, Melody> marshaller;

    @Test
    public void make_a_melody() {
        when(geneticAlgorithm.generate()).thenReturn(mock(Member.class));
        when(marshaller.marshall(any(Member.class))).thenReturn(new Melody());

        MelodyMaker melodyMaker = new MelodyMaker(geneticAlgorithm, marshaller);

        Melody melody = melodyMaker.make();

        assertThat(melody).isNotNull();
    }
}
