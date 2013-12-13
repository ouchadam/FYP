package algorithm.crossover;

import algorithm.Member;

class RandomParentSelector<T> {

    private final RandomTrueFalseGenerator trueFalseGenerator;

    public RandomParentSelector() {
        this(new RandomTrueFalseGenerator());
    }

    RandomParentSelector(RandomTrueFalseGenerator trueFalseGenerator) {
        this.trueFalseGenerator = trueFalseGenerator;
    }

    public T getParent(T parentX, T parentY) {
        return trueFalseGenerator.get() ? parentY : parentX;
    }

}
