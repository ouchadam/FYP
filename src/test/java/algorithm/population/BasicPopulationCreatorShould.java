package algorithm.population;

import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Spy;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class BasicPopulationCreatorShould extends TestWithMocks {

    @Spy MemberFactory memberFactory;

    BasicPopulationCreator basicPopulationCreator;

    @Override
    protected void before() {
        basicPopulationCreator = new BasicPopulationCreator(memberFactory);
    }

    @Test
    public void create_two_random_parents() throws Exception {
        basicPopulationCreator.create();

        verify(memberFactory, times(2)).createRandomParentMember();
    }

    @Test
    public void create_a_population_with_a_size_of_10() throws Exception {
        Population population = basicPopulationCreator.create();

        assertThat(population.size()).isEqualTo(BasicPopulationCreator.POPULATION_SIZE);
    }

}
