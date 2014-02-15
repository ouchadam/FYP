package com.ouchadam.fyp.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomIndexCreator {

    Random random = new Random();

    public List<Integer> create(int count, int max) {
        List<Integer> indexes = new ArrayList<Integer>(count);
        while (indexes.size() < count) {
            Integer randomIndex = random.nextInt(max);
            if (!indexes.contains(randomIndex)) {
                indexes.add(randomIndex);
            }
        }
        return indexes;
    }

}
