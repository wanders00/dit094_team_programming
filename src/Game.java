public class Game {

    public enum Layout {
        NORMAL;
    }

    Layout currentLayout = Layout.NORMAL; // Should be local file

    private Snake snake;
    private int seed;
    private int width;
    private int height;
    private GameObject[][] grid;
    private Difficulty difficulty;
    // Sound sound;
    // Resolution resolution;

    Game(int seed, int width, int height, Difficulty difficulty) {
        this.snake = new Snake();
        this.grid = generateMap(height, width, currentLayout);
        this.width = width;
        this.height = height;
        this.difficulty = difficulty;
    }

    public GameObject[][] generateMap(int height, int width, Layout layout) {
        GameObject[][] gameGrid;
        switch (layout) {
            case NORMAL:
                gameGrid = generateNormalMap(height, width);
                break;
            default:
                gameGrid = null;
                break;
        }
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

    public void update() {
        // will update the grid with timer
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
