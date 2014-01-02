package algorithm;

import algorithm.population.*;
import org.junit.Test;
import org.mockito.Mock;

import helper.TestWithMocks;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class GeneticAlgorithmShould extends TestWithMocks {

    private GeneticAlgorithm geneticAlgorithm;

    @Mock
    PopulationCreator populationCreator;
    @Mock
    PopulationEvolver populationEvolver;
    @Mock
    FittestMemberFinder fittestMemberFinder;

    @Override
    protected void before() {
        geneticAlgorithm = new GeneticAlgorithm(populationCreator, populationEvolver, fittestMemberFinder);
    }

    @Test
    public void create_an_initial_population() throws Exception {
        geneticAlgorithm.generate();

        verify(populationCreator).create();
    }

    @Test
    public void evolve_the_population() throws Exception {
        geneticAlgorithm.generate();

        verify(populationEvolver).evolve(any(Population.class));
    }

    @Test
    public void find_the_fittest_member() throws Exception {
        geneticAlgorithm.generate();

        verify(fittestMemberFinder).find(any(Population.class));
    }
}
