package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.ForEachPair;

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
        return indexes.contains(currentIndex);
    }

    public void forEach(List<Integer> indexes, ForEachPair<Integer> forEach) {
        for (int index = 0; index < indexes.size(); index += 2) {
            int x = indexes.get(index);
            int y = indexes.size() >= (index + 1) ? indexes.get(0) : indexes.get(index + 1);
            forEach.on(x, y);
        }
    }
}
