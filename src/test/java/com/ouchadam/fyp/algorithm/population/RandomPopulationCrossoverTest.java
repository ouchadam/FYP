package com.ouchadam.fyp.algorithm.population;

import com.ouchadam.fyp.algorithm.IndexManager;
import com.ouchadam.fyp.algorithm.domain.Member;
import com.ouchadam.fyp.algorithm.domain.NoteType;
import com.ouchadam.fyp.algorithm.domain.NoteValue;
import com.ouchadam.fyp.algorithm.crossover.Crossover;
import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RandomPopulationCrossoverTest extends TestWithMocks {

    static final int CROSSOVER_COUNT = 1;

    @Mock Random random;
    @Mock Crossover<NoteValue> valueCrossover;
    @Mock Crossover<NoteType> typeCrossover;
    @Mock IndexManager indexManager;
    @Mock Member.Controller memberController;

    private RandomPopulationCrossover randomPopulationCrossover;

    @Override
    protected void before() {
        randomPopulationCrossover = new RandomPopulationCrossover(random, valueCrossover, typeCrossover, indexManager, memberController);
    }

    @Test
    public void crossover_the_note_type_twice_for_every_child() throws Exception {
        when(random.nextInt(anyInt())).thenReturn(CROSSOVER_COUNT);
        when(indexManager.create(anyInt(), anyInt())).thenReturn(createIndexes());

        randomPopulationCrossover.crossover(createPopulation());

        verify(typeCrossover, times(2 * Member.CHILD_COUNT)).crossover(any(NoteType.class), any(NoteType.class));
    }

    @Test
    public void crossover_the_note_value_twice_for_every_child() throws Exception {
        when(random.nextInt(anyInt())).thenReturn(CROSSOVER_COUNT);
        when(indexManager.create(anyInt(), anyInt())).thenReturn(createIndexes());

        randomPopulationCrossover.crossover(createPopulation());

        verify(valueCrossover, times(2 * Member.CHILD_COUNT)).crossover(any(NoteValue.class), any(NoteValue.class));
    }

    @Test
    public void have_a_resulting_size_of_the_double_the_crossover_count() throws Exception {
        List<Integer> indexes = createIndexes();
        when(random.nextInt(anyInt())).thenReturn(CROSSOVER_COUNT);
        when(indexManager.create(anyInt(), anyInt())).thenReturn(indexes);

        Population result = randomPopulationCrossover.crossover(createPopulation());

        assertThat(result.size()).isEqualTo(2 * CROSSOVER_COUNT);
    }

    private List<Integer> createIndexes() {
        List<Integer> indexes = new ArrayList<Integer>(CROSSOVER_COUNT);
        indexes.add(0);
        return indexes;
    }

    private Population createPopulation() {
        List<Member> members = new ArrayList<Member>();
        members.add(MemberHelper.createRandom());
        members.add(MemberHelper.createRandom());
        members.add(MemberHelper.createRandom());
        members.add(MemberHelper.createRandom());
        return new Population(members);
    }
}
