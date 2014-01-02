package algorithm.population;

import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class PopulationMutatorShould extends TestWithMocks {

    @Mock Population population;

    PopulationMutator populationMutator;

    @Override
    protected void before() {
        populationMutator = new PopulationMutator();
    }

    @Test
    public void return_a_population_of_the_same_size_as_the_original() {
        int populationSize = 10;
        when(population.getMembers()).thenReturn(MemberHelper.createMockMembers(populationSize));

        Population mutatedPopulation = populationMutator.mutate(population);

        assertThat(mutatedPopulation.size()).isEqualTo(populationSize);
    }

}
