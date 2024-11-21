package enums;

/**
 * récupère une valeur entière associée de manière unique à l'énuméré
 */

public enum Direction {
    SOUTH(0),
    WEST(1),
    NORTH(2),
    EAST(3),
    NONE(4);

    private int frameLineNumber;

    Direction(int frameLineNumber) {
        this.frameLineNumber = frameLineNumber;
    }

    public int getFrameLineNumber() {
        return frameLineNumber;
    }


}
