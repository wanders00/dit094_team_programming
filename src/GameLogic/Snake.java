package GameLogic;

import java.util.ArrayList;
import java.util.Arrays;

public class Snake {
    private final static int initialBodyLength = 6;

    // The snake object contains the coordinates of each of its' bodyparts.
    // With the getters and setters to utilise this information in the game.
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

    public void setRow(int bodyPart, int row) {
        this.body.get(bodyPart).set(0, row);
    }

    public void setColumn(int bodyPart, int column) {
        this.body.get(bodyPart).set(1, column);
    }

    public void setPosition(int bodyPart, int row, int column) {
        setRow(bodyPart, row);
        setColumn(bodyPart, column);
    }

    public int getRow(int bodyPart) {
        return this.body.get(bodyPart).get(0);
    }

    public int getColumn(int bodyPart) {
        return this.body.get(bodyPart).get(1);
    }

    public ArrayList<ArrayList<Integer>> getBody() {
        return this.body;
    }
}
