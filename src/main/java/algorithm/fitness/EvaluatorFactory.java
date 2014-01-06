package algorithm.fitness;

import algorithm.gene.Feature;
import algorithm.gene.Gene;

public class EvaluatorFactory {

    public FitnessEvaluator<Gene<? extends Feature<?>, ?>> get(EvaluatorType evaluatorType) {
        switch (evaluatorType) {
            case NOTE:
                return new NoteFitness();

            case LENGTH:
                return new LengthFitness();

            case REST:
                return new RestFitness();

            case OCTAVE:
                return new OctaveFitness();

            default:
                throw new RuntimeException("Unhandled evaluator type : " + evaluatorType.name());
        }
    }

}
