package algorithm.crossover.binary;

import java.util.Random;

import algorithm.crossover.Crossover;

public class BinaryUniformCrossover implements Crossover<Binary> {

    private final Random random;

    public static BinaryUniformCrossover newInstance() {
        return new BinaryUniformCrossover(new Random());
    }

    BinaryUniformCrossover(Random random) {
        this.random = random;
    }

    @Override
    public Binary crossover(Binary parentX, Binary parentY) {
        int length = parentX.wordLength();
        int[] crossovers = createCrossovers(length);
        String y = parentY.getValue();
        for (int index = 0; index < crossovers.length; index++) {
            y = replaceCharAt(y, index, y.charAt(crossovers[index]));
        }
        return new Binary(y);
    }

    private int[] createCrossovers(int wordLength) {
        int crossoverCount = random.nextInt(wordLength);
        int[] crossovers = new int[crossoverCount];
        for (int index = 0; index < crossoverCount; index++) {
            crossovers[index] = random.nextInt(wordLength);
        }
        return crossovers;
    }

    public String replaceCharAt(String s, int position, char c) {
        return s.substring(0, position) + c + s.substring(position + 1);
    }
}
