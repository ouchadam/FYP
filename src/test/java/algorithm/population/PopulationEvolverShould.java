package algorithm.population;

import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class PopulationEvolverShould extends TestWithMocks {

    @Mock PopulationMutator populationMutator;
    @Mock PopulationCrosser populationCrosser;
    @Mock Population mockPopulation;
    PopulationEvolver populationEvolver;

    @Override
    protected void before() {
        populationEvolver = new PopulationEvolver(populationMutator, populationCrosser);
    }

    @Test
    public void mutate_the_population() throws Exception {
        populationEvolver.evolve(mockPopulation);

        verify(populationMutator).mutate(any(Population.class));
    }

    @Test
    public void crossover_the_population() throws Exception {
        populationEvolver.evolve(mockPopulation);

        verify(populationCrosser).crossover(any(Population.class));
    }

}
