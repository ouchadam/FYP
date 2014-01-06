package algorithm.gene.feature;

public class FeatureFactory implements NoteCreator {

    public static FeatureFactory newInstance() {
        return new FeatureFactory();
    }

    public Length createLength(int value) {
        return new Length(value);
    }

    @Override
    public Note createNote(int value) {
        return new Note(value);
    }

    public Rest createRest(Rest.Value value) {
        return new Rest(value);
    }

    public Octave createOctave(int value) {
        return new Octave(value);
    }
}
