package algorithm;

import algorithm.population.*;
import helper.MemberHelper;
import org.junit.Test;
import org.mockito.Mock;

import helper.TestWithMocks;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        List<Member> members = new ArrayList<Member>();
        members.add(MemberHelper.createUnfitMember());
        members.add(MemberHelper.createUnfitMember());

        Population population = new Population(members);

        when(populationCreator.create()).thenReturn(population);
        when(populationEvolver.evolve(any(Population.class))).thenReturn(population);
        when(fittestMemberFinder.find(any(Population.class), anyInt())).thenReturn(members);

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

        verify(fittestMemberFinder).find(any(Population.class), anyInt());
    }
}
