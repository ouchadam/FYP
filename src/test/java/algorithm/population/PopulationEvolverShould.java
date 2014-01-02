package algorithm.population;

import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class PopulationEvolverShould extends TestWithMocks {

    @Mock PopulationMutator populationMutator;
    @Mock Population mockPopulation;
    PopulationEvolver populationEvolver;

    @Override
    protected void before() {
        populationEvolver = new PopulationEvolver(populationMutator);
    }

    @Test
    public void mutate_the_population() throws Exception {
        populationEvolver.evolve(mockPopulation);

        verify(populationMutator).mutate(mockPopulation);
    }
    
}
