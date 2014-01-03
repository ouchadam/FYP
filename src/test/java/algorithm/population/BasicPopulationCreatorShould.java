package algorithm.population;

import algorithm.crossover.MemberCrossover;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class BasicPopulationCreatorShould extends TestWithMocks {

    static final int PARENT_OFFSET = 2;
    @Spy MemberFactory memberFactory;
    @Mock MemberCrossover mockCrossover;

    BasicPopulationCreator basicPopulationCreator;

    @Override
    protected void before() {
        basicPopulationCreator = new BasicPopulationCreator(memberFactory, mockCrossover);
    }

    @Test
    public void create_two_random_parents() {
        basicPopulationCreator.create();

        verify(memberFactory, times(2)).createRandomParentMember();
    }

    @Test
    public void create_a_population_with_a_size_of_10() {
        Population population = basicPopulationCreator.create();

        assertThat(population.size()).isEqualTo(BasicPopulationCreator.POPULATION_SIZE);
    }


    @Test
    public void crossover_the_size_of_the_population_minus_the_parents() {
        basicPopulationCreator.create();

        verify(mockCrossover, times(BasicPopulationCreator.POPULATION_SIZE - PARENT_OFFSET)).crossover(any(Member.class), any(Member.class));
    }

}
