public class Snake {

    private Direction currentDirection;

    Snake() {
        this.currentDirection = Direction.UP;
    }

    public Direction getDirection() {
        return this.currentDirection;
    }

    public void updateDirection(Direction newDirection) {
        this.currentDirection = newDirection;
    }

}
