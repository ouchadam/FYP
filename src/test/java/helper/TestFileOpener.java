package helper;

import java.io.File;
import java.io.InputStream;

public class TestFileOpener {

    private static final String POKER_FACE_MIDI = "poker_face.MID";

    public static InputStream pokerFaceMidi() {
        return stream(POKER_FACE_MIDI);
    }

    public static InputStream stream(String name) {
        return classLoader().getResourceAsStream(name);
    }

    public static File file(String name) {
        return new File(classLoader().getResource(name).getPath());
    }

    private static ClassLoader classLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

}
