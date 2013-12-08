package algorithm.crossover;

import java.util.Random;

class RandomTrueFalseGenerator {

    private static final int RANDOM_MAX = 100;
    private static final int MID_POINT = 50;

    public boolean get() {
        int randomNumber = new Random().nextInt(RANDOM_MAX);
        return isMoreThanHalf(randomNumber);
    }

    private boolean isMoreThanHalf(int randomNumber) {
        return randomNumber > MID_POINT;
    }

}
