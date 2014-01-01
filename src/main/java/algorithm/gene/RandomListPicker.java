package algorithm.gene;

import java.util.List;
import java.util.Random;

public class RandomListPicker<T> {

    private final Random random;

    public RandomListPicker() {
        this(new Random());
    }

    RandomListPicker(Random random) {
        this.random = random;
    }

    public T get(List<T> list) {
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

}
