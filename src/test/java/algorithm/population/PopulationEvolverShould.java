package algorithm.population;

import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PopulationEvolverShould extends TestWithMocks {

    @Mock PopulationMutator populationMutator;
    @Mock PopulationCrosser populationCrosser;
    @Mock Population mockPopulation;
    PopulationEvolver populationEvolver;

    @Override
    protected void before() {
        List<Member> members = new ArrayList<Member>();
        members.add(MemberHelper.createUnfitMember());
        members.add(MemberHelper.createUnfitMember());
        members.add(MemberHelper.createUnfitMember());
        members.add(MemberHelper.createUnfitMember());

        when(mockPopulation.getMembers()).thenReturn(members);
        when(mockPopulation.size()).thenReturn(members.size());
        when(populationMutator.mutate(any(Population.class))).thenReturn(mockPopulation);

        populationEvolver = new PopulationEvolver(populationMutator, populationCrosser);
    }

    @Test
    public void mutate_the_population() throws Exception {
        populationEvolver.evolve(mockPopulation);

        verify(populationMutator, atLeastOnce()).mutate(any(Population.class));
    }

    @Test
    public void crossover_the_population() throws Exception {
        populationEvolver.evolve(mockPopulation);

        verify(populationCrosser, atLeastOnce()).crossover(any(Population.class));
    }

}
