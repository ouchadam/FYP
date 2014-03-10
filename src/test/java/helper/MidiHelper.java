package helper;

import com.ouchadam.fyp.analysis.MidiMessageMarshaller;
import com.ouchadam.fyp.analysis.MidiReader;
import com.ouchadam.fyp.analysis.MidiTrack;
import com.ouchadam.fyp.presentation.MidiSystemWrapper;

import java.io.InputStream;

public class MidiHelper {

    public static MidiTrack readPokerFace() {
        return read(TestFileOpener.pokerFaceMidi());
    }

    public static MidiTrack read(InputStream input) {
        try {
            MidiReader midiReader = new MidiReader(new MidiMessageMarshaller(), new MidiSystemWrapper());
            return midiReader.read(input);
        } catch (Exception e) {
            throw new RuntimeException("Error opening midi");
        }
    }

}
