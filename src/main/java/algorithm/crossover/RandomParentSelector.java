package algorithm.crossover;

import algorithm.Member;

class RandomParentSelector {

    private final RandomTrueFalseGenerator trueFalseGenerator;

    public RandomParentSelector() {
        this(new RandomTrueFalseGenerator());
    }

    RandomParentSelector(RandomTrueFalseGenerator trueFalseGenerator) {
        this.trueFalseGenerator = trueFalseGenerator;
    }

    public Member getParent(Member parentX, Member parentY) {
        return trueFalseGenerator.get() ? parentY : parentX;
    }

}
