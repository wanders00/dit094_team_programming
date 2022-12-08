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

}
