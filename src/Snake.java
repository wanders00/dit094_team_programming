public class Snake {

    public enum Direction {
        Up,
        Down,
        Left,
        Right
    }

    private Direction currentDirection;

    Snake() {
        this.currentDirection = Direction.Up;
    }

    public Direction getDirection() {
        return this.currentDirection;
    }

    public void updateDirection(Direction newDirection) {
        this.currentDirection = newDirection;
    }

}
