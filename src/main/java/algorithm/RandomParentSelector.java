package algorithm;

import java.util.Random;

class RandomParentSelector {

    public Member getParent(Member parentX, Member parentY) {
        int randomNumber = new Random().nextInt(100);
        return randomNumber > 50 ? parentY : parentX;
    }

}
