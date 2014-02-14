package com.ouchadam.fyp.algorithm;

import java.util.Random;

class RandomIndexCreator {

    public int[] create(int count, int max) {
        Random random = new Random();
        int[] noteIndexes = new int[count];
        for (int index = 0; index < count; index++) {
            noteIndexes[index] = random.nextInt(max);
        }
        return noteIndexes;
    }

}
