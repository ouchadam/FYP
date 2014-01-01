package algorithm.population;

import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
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
        when(population.getMembers()).thenReturn(createMembers(populationSize));

        Population mutatedPopulation = populationMutator.evolve(population);

        assertThat(mutatedPopulation.size()).isEqualTo(populationSize);
    }

    private List<Member> createMembers(int memberCount) {
        List<Member> members = new ArrayList<Member>();
        for (int index = 0; index < memberCount; index++) {
            members.add(mock(Member.class));
        }
        return members;
    }

}
