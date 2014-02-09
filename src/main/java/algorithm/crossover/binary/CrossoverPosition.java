package algorithm.crossover.binary;

class CrossoverPosition {

    private static final int UNAVAILABLE_RAW_POSITION = -1;
    private final Position fixedPosition;
    private final int customPosition;

    public static CrossoverPosition from(int position) {
        return new CrossoverPosition(null, position);
    }

    public static CrossoverPosition from(Position position) {
        return new CrossoverPosition(position, UNAVAILABLE_RAW_POSITION);
    }

    CrossoverPosition(Position fixedPosition, int customPosition) {
        this.fixedPosition = fixedPosition;
        this.customPosition = customPosition;
    }

    enum Position {
        START,
        MID,
        END,
    }

    public int get(int length) {
        if (customPosition != UNAVAILABLE_RAW_POSITION) {
            return customPosition;
        }
        switch (fixedPosition) {
            case START:
                return 0;
            case MID:
                return length / 2;
            case END:
                return length;

            default:
                return 0;
        }
    }
}
