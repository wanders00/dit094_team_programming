import java.util.ArrayList;
import java.util.Arrays;

public class Snake {

    private ArrayList<ArrayList<Integer>> body = new ArrayList<ArrayList<Integer>>();
    private Direction currentDirection;
    private Direction newDirection;

    Snake(int x, int y) {
        this.growSnake(x, y);
        this.currentDirection = Direction.UP;
        this.newDirection = currentDirection;
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

    public void setRow(int x, int y) {
        this.body.get(x).set(0, y);
    }

    public void setColumn(int x, int y) {
        this.body.get(x).set(1, y);
    }

    public int getRow(int x) {
        return this.body.get(x).get(0);
    }

    public int getColumn(int x) {
        return this.body.get(x).get(1);
    }

    public ArrayList<ArrayList<Integer>> getBody() {
        return this.body;
    }
}
