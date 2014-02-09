package algorithm.crossover.binary;

public class BinaryPadder {

    private static final int NO_PADDING_NEDDED = 0;
    private static final String ZERO_PAD = "0";

    public MatchedLengthBinary pad(Binary binaryX, Binary binaryY) {
        int delta = getPaddingDelta(binaryX.getValue(), binaryY.getValue());
        if (delta == NO_PADDING_NEDDED) {
            return new MatchedLengthBinary(binaryX, binaryY);
        } else {
            Binary min = getMinLength(binaryX, binaryY);
            Binary max = getMaxLength(binaryX, binaryY);
            return createWithOriginalOrder(padBinary(min, delta), max, binaryX, binaryY);
        }
    }

    private Binary getMaxLength(Binary x, Binary y) {
        return x.hasLargerWordLengthThan(y) ? x : y;
    }

    private Binary getMinLength(Binary x, Binary y) {
        return x.hasLargerWordLengthThan(y) ? y : x;
    }

    private int getPaddingDelta(String binaryX, String binaryY) {
        return Math.abs(binaryX.length() - binaryY.length());
    }

    private Binary padBinary(Binary binaryToPad, int delta) {
        String binaryValue = binaryToPad.getValue();
        for (int index = 0; index < delta; index++) {
            binaryValue = ZERO_PAD + binaryValue;
        }
        return new Binary(binaryValue);
    }

    private MatchedLengthBinary createWithOriginalOrder(Binary min, Binary max, Binary x, Binary y) {
        return min.toDecimal() == x.toDecimal() ? new MatchedLengthBinary(min, max) : new MatchedLengthBinary(max, min);
    }

    public static class MatchedLengthBinary {

        public final Binary binaryX;
        public final Binary binaryY;

        public MatchedLengthBinary(Binary binaryX, Binary binaryY) {
            this.binaryX = binaryX;
            this.binaryY = binaryY;
        }
    }

}
