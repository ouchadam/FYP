package helper;

import java.util.ArrayList;
import java.util.List;

public class IndexHelper {

    public static List<Integer> createIndexes(int... positions) {
        List<Integer> indexes = new ArrayList<Integer>(positions.length);
        for (int position : positions) {
            indexes.add(position);
        }
        return indexes;
    }

}
