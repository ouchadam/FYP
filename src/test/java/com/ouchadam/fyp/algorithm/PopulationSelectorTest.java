package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.population.Population;
import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class PopulationSelectorTest extends TestWithMocks {

    static final int SKIP_ZERO_OFFSET = 1;

    @Mock Random random;
    @Mock Tournament tournament;

    private PopulationSelector populationSelector;

    @Override
    protected void before() {
        populationSelector = new PopulationSelector(random, tournament);
    }

    @Test
    public void create_the_seeds_from_the_randomly_selected_size() {
        int populationSize = 10;
        int newPopulationSize = 2;
        when(random.nextInt(populationSize - SKIP_ZERO_OFFSET)).thenReturn(newPopulationSize);

        Population seeds = populationSelector.selectSeeds(createPopulation(populationSize));

        assertThat(seeds.size()).isEqualTo(newPopulationSize + SKIP_ZERO_OFFSET);
    }

    @Test
    public void create_the_seeds_using_tournament() {
        int populationSize = 10;
        int newPopulationSize = 2;
        when(random.nextInt(populationSize - SKIP_ZERO_OFFSET)).thenReturn(newPopulationSize);

        populationSelector.selectSeeds(createPopulation(populationSize));

        verify(tournament, times(newPopulationSize + SKIP_ZERO_OFFSET)).tournament(any(Population.class), any(Member.class), any(Member.class));
    }

    @Test
    public void get_the_best_via_population_cut_at_5_percent() {
        int populationSize = 20;
        int fivePercentOfTwenty = 1;

        Population best = populationSelector.getBest(createPopulation(populationSize));

        assertThat(best.size()).isEqualTo(fivePercentOfTwenty);
    }


    private Population createPopulation(int size) {
        List<Member> memberList = new ArrayList<Member>();
        for (int index = 0; index < size; index++) {
            memberList.add(MemberHelper.createRandom());
        }
        return new Population(memberList);
    }
}
