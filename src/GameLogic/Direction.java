package GameLogic;
//Keybind to control the direction in which our snake moves.
public enum Direction {//An enum that represents a set of constants(directions) through the implemantation of keybind
    UP(Keybind.UP),
    DOWN(Keybind.DOWN),
    LEFT(Keybind.LEFT),
    RIGHT(Keybind.RIGHT);

    private Keybind keybind;//initializing a new keybind as part of the Direction attributes.

    Direction(Keybind keybind) {
        this.keybind = keybind;
    }

    public Keybind getKeybind() {
        return this.keybind;
    }

    public Direction getOppositeDirection() {//Method return the opposite direction in wich the snake moves.
        switch (this) {
            case DOWN:
                return Direction.UP;
            case LEFT:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.LEFT;
            case UP:
                return Direction.DOWN;
            default:
                return null;
        }
    }
}
