package algorithm.fitness;

import algorithm.gene.Chromosome;
import algorithm.gene.Feature;
import algorithm.gene.Gene;
import algorithm.population.Member;

public class MemberFitness implements FitnessEvaluator<Member> {

    private final EvaluatorFactory evaluatorFactory;

    public MemberFitness(EvaluatorFactory evaluatorFactory) {
        this.evaluatorFactory = evaluatorFactory;
    }

    @Override
    public FitnessValue evaluate(Member what) {
        int count = 0;
        int sum = 0;
        for (int index = 0; index < what.count(); index++) {
            Chromosome chromosome = what.get(index);
            for (int geneIndex = 0; geneIndex < chromosome.count(); geneIndex++) {
                Gene<? extends Feature<?>, ?> gene = chromosome.getGene(geneIndex);
                sum += evaluateGene(gene).value();
                count++;
            }
        }
        return new FitnessValue(createValue(count, sum));
    }

    private int createValue(int count, int sum) {
        return count == 0 || sum == 0 ? sum : sum / count;
    }

    private FitnessValue evaluateGene(Gene<? extends Feature<?>, ?> gene) {
        EvaluatorType evaluatorType = gene.getEvaluatorType();
        FitnessEvaluator<Gene<? extends Feature<?>, ?>> evaluator = evaluatorFactory.get(evaluatorType);
        return evaluator.evaluate(gene);
    }

}
