package algorithm.crossover.binary;

import helper.TestWithMocks;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class BinaryCrossoverShould extends TestWithMocks {

    private static final Binary BINARY_1 = new Binary("0001");
    private static final Binary BINARY_10 = new Binary("1010");

    /*

        crossover combinations

        Original parents :
        parent x    0 | 0 | 0 | 1 = 1
        parent y    1 | 0 | 1 | 0 = 10

        0 : No crossover point - defaults to Parent Y

             0  |  0  |  0  |  1
            [1] | [0] | [1] | [0]    = 1 0 1 0 = 10

        1 :

            [0] |  0  |  0  |  1
             1  | [0] | [1] | [0]    = 0 0 1 0 = 2


        2 :

            [0] | [0] |  0  |  1
             1  |  0  | [1] | [0]    = 0 0 1 0 = 2

        3 :


            [0] | [0] | [0] |  1
             1  |  0  |  1  | [0]    = 0 0 0 0 = 0

        4 :

            [0] | [0] | [0] | [1]
             1  |  0  |  1  |  0     = 0 0 0 1 = 1

    */

    @Test
    public void prove_combination_zero() {
        int position = 0;
        BinarySinglePointCrossover crossover = new BinarySinglePointCrossover(position);

        Binary crossoverResult = crossover.crossover(BINARY_1, BINARY_10);

        assertThat(crossoverResult.getValue()).isEqualTo("1010");
    }


    @Test
    public void prove_combination_one() {
        int position = 1;
        BinarySinglePointCrossover crossover = new BinarySinglePointCrossover(position);

        Binary crossoverResult = crossover.crossover(BINARY_1, BINARY_10);

        assertThat(crossoverResult.getValue()).isEqualTo("0010");
    }

    @Test
    public void prove_combination_two() {
        int position = 2;
        BinarySinglePointCrossover crossover = new BinarySinglePointCrossover(position);

        Binary crossoverResult = crossover.crossover(BINARY_1, BINARY_10);

        assertThat(crossoverResult.getValue()).isEqualTo("0010");
    }


    @Test
    public void prove_combination_three() {
        int position = 3;
        BinarySinglePointCrossover crossover = new BinarySinglePointCrossover(position);

        Binary crossoverResult = crossover.crossover(BINARY_1, BINARY_10);

        assertThat(crossoverResult.getValue()).isEqualTo("0000");
    }

    @Test
    public void prove_combination_four() {
        int position = 4;
        BinarySinglePointCrossover crossover = new BinarySinglePointCrossover(position);

        Binary crossoverResult = crossover.crossover(BINARY_1, BINARY_10);

        assertThat(crossoverResult.getValue()).isEqualTo("0001");
    }

}
