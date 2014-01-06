package algorithm.fitness;

import algorithm.gene.feature.Rest;
import algorithm.population.Member;
import helper.MemberHelper;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;

public class MemberFitnessShould extends TestWithMocks {

    @Mock FitnessEvaluator<Integer> lengthEvaluator;
    @Mock FitnessEvaluator<Integer> octaveEvaluator;
    @Mock FitnessEvaluator<Integer> noteEvaluator;
    @Mock FitnessEvaluator<Rest.Value> restEvaluator;

    @Mock EvaluatorFactory evaluatorFactory;

    MemberFitness memberFitness;

    @Override
    protected void before() {
        memberFitness = new MemberFitness(new EvaluatorFactory());
    }

    @Test
    public void give_unfit_members_a_minimum_fitness() {
        Member unfitMember = MemberHelper.createUnfitMember();

        FitnessValue fitnessValue = memberFitness.evaluate(unfitMember);

        assertThat(fitnessValue.isMin()).isTrue();
    }


    @Test
    public void give_fit_members_a_maximum_fitness() {
        Member fitMember = MemberHelper.createFitMember();

        FitnessValue fitnessValue = memberFitness.evaluate(fitMember);

        assertThat(fitnessValue.isMax()).isTrue();
    }

}
