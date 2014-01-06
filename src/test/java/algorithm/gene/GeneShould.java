package algorithm.gene;

import algorithm.fitness.EvaluatorType;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GeneShould extends TestWithMocks {

    @Mock Mutator<StubFeature> stubMutator;
    @Mock StubFeature mockFeature;

    @Test
    public void mutate_internal_feature() {
        StubFeature stubFeature = new StubFeature();
        Gene<StubFeature, Void> gene = new Gene<StubFeature, Void>(stubFeature, stubMutator);

        gene.mutate();

        verify(stubMutator).mutate(stubFeature);
    }

    @Test
    public void get_the_value_from_the_feature() {
        Gene<StubFeature, Void> gene = new Gene<StubFeature, Void>(mockFeature, stubMutator);

        gene.getValue();

        verify(mockFeature).getValue();
    }

    @Test
    public void get_the_evaluator_type_from_the_feature() {
        Gene<StubFeature, Void> gene = new Gene<StubFeature, Void>(mockFeature, stubMutator);

        gene.getEvaluatorType();

        verify(mockFeature).getEvaluatorType();
    }

    private static class StubFeature implements Feature<Void> {
        @Override
        public Void getValue() {
            return null;
        }

        @Override
        public EvaluatorType getEvaluatorType() {
            return null;
        }
    }

}
