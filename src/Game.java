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

    Game(int seed, int width, int height, Difficulty difficulty) {
        this.snake = new Snake(width / 2, height / 2);
        this.layout = currentLayout; //do something with seed argument
        this.width = width;
        this.height = height;
        this.difficulty = difficulty;
        this.grid = generateMap(height, width);
    }

    public GameObject[][] generateMap(int height, int width) {
        GameObject[][] gameGrid;
        switch (this.layout) {
            case NORMAL:
                gameGrid = generateNormalMap(height, width);
                break;
            default:
                gameGrid = null;
                break;
        }
        gameGrid[height / 2][width / 2] = new SnakeSegment();
        return gameGrid;
    }

    private GameObject[][] generateNormalMap(int height, int width) {
        GameObject[][] normalGameGrid = new GameObject[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 00 || j == width - 1) {
                    normalGameGrid[i][j] = new OrdinaryWall();
                } else {
                    normalGameGrid[i][j] = new EmptyGameObject();
                }
            }
        }
        return normalGameGrid;
    }

    public boolean update() {
        switch (this.snake.getDirection()) {
            case DOWN:
                if (this.grid[this.snake.getRowCoordinate() + 1][this.snake
                        .getColumnCoordinate()] instanceof WallMapObject) {
                    return false;
                } else {
                    this.grid[this.snake.getRowCoordinate() + 1][this.snake.getColumnCoordinate()] = new SnakeSegment();
                    this.grid[this.snake.getRowCoordinate()][this.snake.getColumnCoordinate()] = new EmptyGameObject();
                    this.snake.setRowCoordinate(this.snake.getRowCoordinate() + 1);
                }
                break;
            case LEFT:
                if (this.grid[this.snake.getColumnCoordinate() - 1][this.snake
                        .getColumnCoordinate()] instanceof WallMapObject) {
                    return false;
                } else {
                    this.grid[this.snake.getRowCoordinate()][this.snake.getColumnCoordinate() - 1] = new SnakeSegment();
                    this.grid[this.snake.getRowCoordinate()][this.snake.getColumnCoordinate()] = new EmptyGameObject();
                    this.snake.setColumnCoordinate(this.snake.getColumnCoordinate() - 1);
                }
                break;
            case RIGHT:
                if (this.grid[this.snake.getColumnCoordinate() + 1][this.snake
                        .getColumnCoordinate()] instanceof WallMapObject) {
                    return false;
                } else {
                    this.grid[this.snake.getRowCoordinate()][this.snake.getColumnCoordinate() + 1] = new SnakeSegment();
                    this.grid[this.snake.getRowCoordinate()][this.snake.getColumnCoordinate()] = new EmptyGameObject();
                    this.snake.setColumnCoordinate(this.snake.getColumnCoordinate() + 1);
                }
                break;
            case UP:
                if (this.grid[this.snake.getRowCoordinate() - 1][this.snake
                        .getColumnCoordinate()] instanceof WallMapObject) {
                    return false;
                } else {
                    this.grid[this.snake.getRowCoordinate() - 1][this.snake.getColumnCoordinate()] = new SnakeSegment();
                    this.grid[this.snake.getRowCoordinate()][this.snake.getColumnCoordinate()] = new EmptyGameObject();
                    this.snake.setRowCoordinate(this.snake.getRowCoordinate() - 1);
                }
                break;
        }
        return true;
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
