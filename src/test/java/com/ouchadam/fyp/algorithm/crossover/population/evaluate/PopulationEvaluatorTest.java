package com.ouchadam.fyp.algorithm.crossover.population.evaluate;

import java.util.ArrayList;
import java.util.List;

import com.ouchadam.fyp.algorithm.Note;
import org.junit.Test;
import org.mockito.Mock;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import com.ouchadam.fyp.algorithm.crossover.population.Evaluation;
import com.ouchadam.fyp.algorithm.crossover.population.Population;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.fitness.FitnessEvaluator;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.fitness.FitnessRule;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.fitness.FitnessValue;
import helper.MemberHelper;
import helper.Printer;
import helper.TestWithMocks;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PopulationEvaluatorTest extends TestWithMocks {

    @Mock
    FitnessFactory fitnessFactory;
    private PopulationEvaluator populationEvaluator;

    @Override
    protected void before() {
        populationEvaluator = new PopulationEvaluator(fitnessFactory);
    }

    @Test
    public void return_max_fitness_when_all_members_are_max_fitness() {
        forcePerfectFitness();
        Population population = createRandomPopulation();

        Evaluation evaluate = populationEvaluator.evaluate(population);

        assertThat(evaluate.fitnessValue()).isEqualTo(FitnessValue.max());
    }

    private void forcePerfectFitness() {
        FitnessEvaluator<Member> mockEvaluator = mock(FitnessEvaluator.class);
        when(mockEvaluator.evaluate(any(Member.class), (FitnessRule<Member>) anyVararg())).thenReturn(FitnessValue.max());
        when(fitnessFactory.member()).thenReturn(mockEvaluator);
    }

    private Population createRandomPopulation() {
        List<Member> members = new ArrayList<Member>();
        members.add(MemberHelper.createRandom());
        members.add(MemberHelper.createRandom());
        members.add(MemberHelper.createRandom());
        members.add(MemberHelper.createRandom());
        return new Population(members);
    }

    @Test
    public void testName() {
        when(fitnessFactory.member()).thenReturn(new MemberEvaluator());

        List<Member> members = new ArrayList<Member>(4);
        Member perfectMember = createMember(60);
        members.add(createMember(50));
        members.add(createMember(60));
        members.add(createMember(10));
        members.add(createMember(2));

        Population population = new Population(members);

        Evaluation evaluation = populationEvaluator.evaluate(population);

        Printer.print(evaluation.population());

        assertThat(evaluation.population().get(0)).isEqualTo(perfectMember);
    }

    private Member createMember(int noteValue) {
        List<Note> notes = new ArrayList<Note>(2);
        notes.add(new Note(new Binary(Integer.toBinaryString(noteValue))));
        return new Member(notes);
    }

}
