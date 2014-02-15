package com.ouchadam.fyp.algorithm;

import java.util.List;

public class IndexManager {

    private final RandomIndexCreator randomIndexCreator;

    public IndexManager(RandomIndexCreator randomIndexCreator) {
        this.randomIndexCreator = randomIndexCreator;
    }

    public List<Integer> create(int count, int max) {
        return randomIndexCreator.create(count, max);
    }

    public boolean isIndex(Integer currentIndex, List<Integer> indexes) {
        for (Integer index : indexes) {
            if (index.equals(currentIndex)) {
                return true;
            }
        }
        return false;
    }

}
