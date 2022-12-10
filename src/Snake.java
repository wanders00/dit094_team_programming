public class Snake {

    private int rowCoordinate;
    private int columnCoordinate;
    private Direction currentDirection;

    Snake(int x, int y) {
        this.rowCoordinate = x;
        this.columnCoordinate = y;
        this.currentDirection = Direction.UP;
    }

    public Direction getDirection() {
        return this.currentDirection;
    }

    public void setDirection(Direction newDirection) {
        this.currentDirection = newDirection;
    }

    public void setRowCoordinate(int x) {
        this.rowCoordinate = x;
    }

    public void setColumnCoordinate(int y) {
        this.columnCoordinate = y;
    }

    public int getRowCoordinate() {
        return this.rowCoordinate;
    }

    public int getColumnCoordinate() {
        return this.columnCoordinate;
    }
}
