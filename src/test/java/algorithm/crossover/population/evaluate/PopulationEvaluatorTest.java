package algorithm.crossover.population.evaluate;

import algorithm.Member;
import algorithm.crossover.population.Evaluation;
import algorithm.crossover.population.Population;
import algorithm.crossover.population.evaluate.fitness.FitnessEvaluator;
import algorithm.crossover.population.evaluate.fitness.FitnessValue;
import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PopulationEvaluatorTest extends TestWithMocks {

    @Mock FitnessFactory fitnessFactory;

    @Test
    public void testName() {

        forcePerfectFitness();


        PopulationEvaluator populationEvaluator = new PopulationEvaluator(fitnessFactory);
        Population population = createRandomPopulation();

        Evaluation evaluate = populationEvaluator.evaluate(population);

        assertThat(evaluate.fitnessValue()).isEqualTo(FitnessValue.max());
    }

    private void forcePerfectFitness() {
        FitnessEvaluator mockEvaluator = mock(FitnessEvaluator.class);
        when(mockEvaluator.evaluate()).thenReturn(FitnessValue.max());
        when(fitnessFactory.member(any(Member.class))).thenReturn(mockEvaluator);
    }

    private Population createRandomPopulation() {
        List<Member> members = new ArrayList<Member>();
        members.add(MemberHelper.createRandom());
        members.add(MemberHelper.createRandom());
        members.add(MemberHelper.createRandom());
        members.add(MemberHelper.createRandom());
        return new Population(members);
    }
}
