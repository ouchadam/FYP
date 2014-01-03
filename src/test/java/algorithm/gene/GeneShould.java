package algorithm.gene;

import algorithm.fitness.FitnessValue;
import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class GeneShould extends TestWithMocks {

    @Mock Mutator<StubFeature> stubMutator;

    @Test
    public void should_mutate_internal_feature() throws Exception {
        StubFeature stubFeature = new StubFeature();
        Gene<StubFeature> gene = new Gene<StubFeature>(stubFeature, stubMutator);

        gene.mutate();

        verify(stubMutator).mutate(stubFeature);
    }

    private static class StubFeature implements Feature<Void> {
        @Override
        public Void getValue() {
            return null;
        }

        @Override
        public FitnessValue getFitness() {
            return null;
        }
    }

}
