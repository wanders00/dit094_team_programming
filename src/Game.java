import java.util.ArrayList;

public class Game {

    private static Layout currentLayout = Layout.NORMAL; // Should be local file

    public enum Layout {
        NORMAL;
    }

    private Snake snake;
    private Layout layout;
    private int width;
    private int height;
    private GameObject[][] grid;
    private Difficulty difficulty;
    // Sound sound;
    // Resolution resolution;

    Game(int width, int height, Difficulty difficulty) {
        this.snake = new Snake(width / 2, height / 2);
        this.layout = currentLayout; // do something with seed argument
        this.width = width;
        this.height = height;
        this.difficulty = difficulty;
        this.grid = generateMap(height, width);
        generateFruit();
    }

    public GameObject[][] generateMap(int height, int width) {
        GameObject[][] gameGrid;
        switch (this.layout) {
            case NORMAL:
                gameGrid = generateNormalMap();
                break;
            default:
                gameGrid = null;
                break;
        }
        gameGrid[height / 2][width / 2] = new SnakeSegment();
        return gameGrid;
    }

    private GameObject[][] generateNormalMap() {
        GameObject[][] normalGameGrid = new GameObject[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (i == 0 || i == height - 1 || j == 00 || j == width - 1) {
                    normalGameGrid[i][j] = new WallGameObject();
                } else {
                    normalGameGrid[i][j] = new EmptyGameObject();
                }
            }
        }
        return normalGameGrid;
    }

    public boolean update() {
        boolean shouldGrowSnake = false;
        int newRow, newColumn, originalRow, originalColumn;
        originalRow = newRow = this.snake.getRow(0);
        originalColumn = newColumn = this.snake.getColumn(0);
        if (predictMovement() instanceof WallGameObject || predictMovement() instanceof SnakeSegment) {
            return false;
        } else if (predictMovement() instanceof FruitGameObject) {
            shouldGrowSnake = true;
            generateFruit();
        }
        switch (this.snake.getDirection()) {
            case DOWN:
                newRow++;
                break;
            case LEFT:
                newColumn--;
                break;
            case RIGHT:
                newColumn++;
                break;
            case UP:
                newRow--;
                break;
        }
        this.grid[newRow][newColumn] = new SnakeSegment();

        for (int i = 0; i < this.snake.getBody().size(); i++) {
            originalColumn = this.snake.getColumn(i);
            originalRow = this.snake.getRow(i);
            this.grid[newRow][newColumn] = new SnakeSegment();
            this.snake.setColumn(i, newColumn);
            this.snake.setRow(i, newRow);
            newRow = originalRow;
            newColumn = originalColumn;
        }

        if (shouldGrowSnake) {
            this.snake.growSnake(originalRow, originalColumn);
        } else {
            this.grid[originalRow][originalColumn] = new EmptyGameObject();
        }
        return true;
    }

    public void generateFruit() {
        boolean createdFruit = true;
        while (createdFruit) {
            int row = (int) (Math.random() * height);
            int column = (int) (Math.random() * width);
            System.out.println(row + " " + column); // delete me later
            if (this.grid[row][column] instanceof EmptyGameObject) {
                this.grid[row][column] = new FruitGameObject();
                createdFruit = false;
            }
        }
    }

    public GameObject predictMovement() {
        return this.grid[predictRow()][predictColumn()];
    }

    public int[] predictCoordinates() {
        switch (this.snake.getDirection()) {
            case DOWN:
                return new int[] { this.snake.getRow(0) + 1, this.snake.getColumn(0) };
            case LEFT:
                return new int[] { this.snake.getRow(0), this.snake.getColumn(0) - 1 };
            case RIGHT:
                return new int[] { this.snake.getRow(0), this.snake.getColumn(0) + 1 };
            case UP:
                return new int[] { this.snake.getRow(0) - 1, this.snake.getColumn(0) };
            default:
                return null;
        }
    }

    public int predictRow() {
        return predictCoordinates()[0];
    }

    public int predictColumn() {
        return predictCoordinates()[1];
    }

    public GameObject[][] getState() {
        return this.grid;
    }

    public Snake getSnake() {
        return this.snake;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }
}
