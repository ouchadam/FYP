package algorithm.gene.feature;

import algorithm.fitness.*;

public class FeatureFactory implements NoteCreator {

    private final FitnessEvaluator<Integer> lengthEvaluator;
    private final FitnessEvaluator<Octave> octaveEvaluator;
    private final FitnessEvaluator<Integer> noteEvaluator;
    private final FitnessEvaluator<Rest.Value> restEvaluator;

    public static FeatureFactory newInstance() {
        return new FeatureFactory(new LengthFitness(), new OctaveFitness(), new NoteFitness(), new RestFitness());
    }

    FeatureFactory(LengthFitness lengthEvaluator, OctaveFitness octaveEvaluator, NoteFitness noteEvaluator, RestFitness restEvaluator) {
        this.lengthEvaluator = lengthEvaluator;
        this.octaveEvaluator = octaveEvaluator;
        this.noteEvaluator = noteEvaluator;
        this.restEvaluator = restEvaluator;
    }

    public Length createLength(int value) {
        return new Length(value, lengthEvaluator);
    }

    @Override
    public Note createNote(int value) {
        return new Note(value, noteEvaluator);
    }

    public Rest createRest(Rest.Value value) {
        return new Rest(value, restEvaluator);
    }

    public Octave createOctave(int value) {
        return new Octave(value, octaveEvaluator);
    }
}
