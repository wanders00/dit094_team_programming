import java.util.ArrayList;
import java.util.Arrays;

public class Snake {
    private static int initialBodyLength = 6;
    private ArrayList<ArrayList<Integer>> body = new ArrayList<ArrayList<Integer>>();
    private Direction currentDirection;
    private Direction newDirection;

    Snake(int initialRow, int initialColumn) {
        this.currentDirection = Direction.UP;
        this.newDirection = currentDirection;
        for (int i = 0; i < initialBodyLength; i++) {
            this.growSnake(initialRow + i, initialColumn);
        }
    }

    public Direction getDirection() {
        return this.currentDirection;
    }

    public void setNewDirection(Direction newDirection) {
        this.newDirection = newDirection;
    }

    public void updateDirection() {
        this.currentDirection = this.newDirection;
    }

    public void growSnake(int row, int column) {
        this.body.add(new ArrayList<>(Arrays.asList(row, column)));
    }

    public void setRow(int bodypart, int row) {
        this.body.get(bodypart).set(0, row);
    }

    public void setColumn(int bodypart, int column) {
        this.body.get(bodypart).set(1, column);
    }

    public int getRow(int bodypart) {
        return this.body.get(bodypart).get(0);
    }

    public int getColumn(int bodypart) {
        return this.body.get(bodypart).get(1);
    }

    public ArrayList<ArrayList<Integer>> getBody() {
        return this.body;
    }
}
