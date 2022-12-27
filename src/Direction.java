public enum Direction {
    UP(Keybind.UP),
    DOWN(Keybind.DOWN),
    LEFT(Keybind.LEFT),
    RIGHT(Keybind.RIGHT);

    private Keybind keybind;

    Direction(Keybind keybind) {
        this.keybind = keybind;
    }

    public Keybind getKeybind() {
        return this.keybind;
    }

    public Direction getOppositeDirection() {
        switch (this) {
            case DOWN:
                return Direction.UP;
            case LEFT:
                return Direction.LEFT;
            case RIGHT:
                return Direction.RIGHT;
            case UP:
                return Direction.DOWN;
            default:
                return null;
        }
    }
}
